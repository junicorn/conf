package com.junicorn.conf;

public class ProxyTest {
	public static void main(String args[]) {
		IRobot robot = (IRobot) java.lang.reflect.Proxy.newProxyInstance(IRobot.class.getClassLoader(),
				new java.lang.Class[] { IRobot.class }, new java.lang.reflect.InvocationHandler() {

					@Override
					public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
							throws java.lang.Throwable {
						String method_name = method.getName();
						Class<?>[] classes = method.getParameterTypes();

						if (method_name.equals("Name")) {
							if (args == null) {
								return "Mr IRobot";
							} else {
								return args[0] + " IRobot";
							}
						} else if (method_name.equals("Talk")) {
							switch (classes.length) {
							case 0:
								System.out.println("Hello");
								break;
							case 1:
								if (classes[0] == int.class) {
									System.out.println("Hi. Int: " + args[0]);
								} else {
									System.out.println("Hi. String: " + args[0]);
								}
								break;
							case 2:
								if (classes[0] == String.class) {
									System.out.println("Hi. String: " + args[0] + ". Int: " + args[1]);
								} else {
									if (classes[1] == String.class) {
										System.out.println("Hi. int: " + args[0] + ". String: " + args[1]);
									} else {
										System.out.println("Hi. int: " + args[0] + ". Int: " + args[1]);
									}
								}
								break;
							}
						}
						return null;
					}
				});

		System.out.println(robot.Name());
		System.out.println(robot.Name("Dr"));
		robot.Talk();
		robot.Talk("stuff");
		robot.Talk(100);
		robot.Talk("stuff", 200);
		robot.Talk(300, 400);
		robot.Talk(500, "stuff");
	}
}