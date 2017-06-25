package com.gemicle.ormmodule.interfaces;

import com.gemicle.ormmodule.pojo.TableInformation;

public interface IChainAnnotation {
	void generateInformation(Class<?> obj, TableInformation tableInformation, IChainAnnotation nextAnnotation);
}
