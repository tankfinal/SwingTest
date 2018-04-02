import jssc.SerialPort;
import jssc.SerialPortException;

public class TTT {

	public static void main(String[] args) throws SerialPortException {
		// TODO Auto-generated method stub
		SerialPort serialPort = new SerialPort("/dev/usb/");
		serialPort.openPort();//Open serial port
		serialPort.setParams(4800, 8, 1, 0);//Set params.
		while(true) {
		    byte[] buffer = serialPort.readBytes(10);
		    if(buffer!=null) {
		        for(byte b:buffer) {
		            System.out.print("test2");
		            
		            
		        }
		    } 
		}
		
	}

}
