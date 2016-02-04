package com.junicorn.conf.adapter;

import java.io.InputStream;

import com.junicorn.conf.Config;

public interface ConfigAdapter {

	Config read(InputStream ins);

}
