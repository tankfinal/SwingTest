
import java.nio.ByteBuffer;

import org.usb4java.Context;
import org.usb4java.Device;
import org.usb4java.DeviceDescriptor;
import org.usb4java.DeviceHandle;
import org.usb4java.DeviceList;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

public class UTMusb 
{
    public static int vid = 5734;
    public static int pid = 2385;
    public static void main(String[] args) 
    {
        Context context = new Context();
        int result = LibUsb.init(context);
        if(result != LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to initialize the usb device",result);
        }
        DeviceList list = new DeviceList();
        result = LibUsb.getDeviceList(null, list);
        if(result < 0 )throw new LibUsbException("Unable to get device list",result);
            try
            {
                for(Device device : list)
                {
                    DeviceDescriptor device_descriptor = new DeviceDescriptor();
                    result = LibUsb.getDeviceDescriptor(device, device_descriptor);
                    if(result != LibUsb.SUCCESS)throw new LibUsbException("Unable to get device descriptor : ",result);
                    System.out.println("Product id is : "+device_descriptor.idProduct()+" "+"Vendor id is : "+device_descriptor.idVendor());
                    if(device_descriptor.idProduct()==5734 && device_descriptor.idVendor()==2385)
                    {
                        System.out.println("Product id and vendor id was matched");
                        getDeviceHandle(device);
                    }
                    else
                    {
                        System.out.println("Product id and vendor id was not matched");
                    }

                }

            }
            finally
            {
                LibUsb.freeDeviceList(list, true);
            }


    }

    public static void getDeviceHandle(Device device)
    {
        DeviceHandle handle = new DeviceHandle();
        int result = LibUsb.open(device, handle);
        if(result!=LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to open the usb device",result);
        }
        try
        {
            claimDevice(handle,0);
        }
        finally
        {
            LibUsb.close(handle);
        }
    }

    private static void claimDevice(DeviceHandle handle, int interfacenumber) {
         int result = LibUsb.claimInterface(handle, interfacenumber);

         if(result!=LibUsb.SUCCESS)
         {
             throw new LibUsbException("Unabl to claim or take the usb interface",result);
         }
         try
         {
             System.out.println("Usb device taken successfully");
             SendData(handle);
         }
        finally
        {
            result = LibUsb.releaseInterface(handle, interfacenumber);
            if(result!=LibUsb.SUCCESS);
            {
                throw new LibUsbException("Unable to release interface ",result);
            }
        }
    }

    private static void SendData(DeviceHandle handle) 
    {
        ByteBuffer sending_buffer= ByteBuffer.allocateDirect(8);
        sending_buffer.put(new byte[]{1,2,3,4,5,6,7,8});
        int transfered = LibUsb.controlTransfer(handle, (byte)(LibUsb.REQUEST_TYPE_CLASS | LibUsb.RECIPIENT_INTERFACE),(byte)0x09, (short)2, (short)1, sending_buffer, 5000);
        if(transfered < 0)
        {
            throw new LibUsbException("Transfering failed",transfered);
        }
        System.out.println(transfered +"Data bytes sent" );
    }

}
