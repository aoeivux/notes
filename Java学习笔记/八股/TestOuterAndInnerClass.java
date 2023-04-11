package com.aoeivux.八股;

import com.aoeivux.八股.Outer.Inner1;

public class TestOuterAndInnerClass {
	public static void main(String[] args) {
		Outer outer = new Outer();
		Inner1 inner1 = outer.new Inner1();
		inner1.test();
		outer.method();
	}
}

class Outer {
    int out_x = 0;

    public void method() {
        //在方法体内部定义的内部类
        class Inner2 {
            public void method() {
				System.out.println("outer->outer:method->inner2->inner:inner");
            }
        }
        Inner2 inner2 = new Inner2();
		inner2.method();
    }

    //在方法体外面定义的内部类
    public class Inner1 {
		public void test() {
			System.out.println("Outer->Innter1");
		}
    }
}
