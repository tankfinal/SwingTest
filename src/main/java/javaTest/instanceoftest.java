package javaTest;

interface A {
}

class B implements A {

}

class C extends B {

}

class instanceoftest {
	public static void main(String[] args) {
		A a = new B();
		B b = new B();
		C c = new C();
		boolean res;

		System.out.println("instanceoftest test case 1: ------------------");
		res = a instanceof A;
		System.out.println("a instanceof A: " + res);

		res = b instanceof B;
		System.out.println("b instanceof B: " + res);

		System.out.println(c instanceof A );
		System.out.println("/ninstanceoftest test case 2: ------------------");
		a = new B();
		b = new B();

		res = a instanceof A;
		System.out.println("a instanceof A: " + res);

		res = a instanceof B;
		System.out.println("a instanceof B: " + res);

		res = b instanceof A;
		System.out.println("b instanceof A: " + res);

		res = b instanceof B;
		System.out.println("b instanceof B: " + res);

		System.out.println("/ninstanceoftest test case 3: ------------------");
		B b2 = (C) new C();

		res = b2 instanceof A;
		System.out.println("b2 instanceof A: " + res);

		res = b2 instanceof B;
		System.out.println("b2 instanceof B: " + res);

		res = b2 instanceof C;
		System.out.println("b2 instanceof C: " + res);
	}
}
