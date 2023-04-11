package com.aoeivux.八股;

/**
 * Demo1SubInterface
 */
public interface Demo1SubInterface extends Demo1Interface, Demo2Interface{

	@Override
	default void hi() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'hi'");
	}

	@Override
	default void walk() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'walk'");
	}
	
	
}