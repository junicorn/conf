package com.junicorn.conf;

interface IRobot {

	String Name();

	String Name(String title);

	void Talk();

	void Talk(String stuff);

	void Talk(int stuff);

	void Talk(String stuff, int more_stuff);

	void Talk(int stuff, int more_stuff);

	void Talk(int stuff, String more_stuff);
}