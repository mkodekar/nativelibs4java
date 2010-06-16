package com.nativelibs4java.mono.library;
/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a>, <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class MonoObject extends com.ochafik.lang.jnaerator.runtime.Structure<MonoObject, MonoObject.ByValue, MonoObject.ByReference> {
	/// C type : MonoVTable*
	public com.nativelibs4java.mono.library.MonoLibrary.MonoVTable vtable;
	/// C type : MonoThreadsSync*
	public com.nativelibs4java.mono.library.MonoLibrary.MonoThreadsSync synchronisation;
	public MonoObject() {
		super();
	}
	/**
	 * @param vtable C type : MonoVTable*<br>
	 * @param synchronisation C type : MonoThreadsSync*
	 */
	public MonoObject(com.nativelibs4java.mono.library.MonoLibrary.MonoVTable vtable, com.nativelibs4java.mono.library.MonoLibrary.MonoThreadsSync synchronisation) {
		super();
		this.vtable = vtable;
		this.synchronisation = synchronisation;
	}
	protected ByReference newByReference() { return new ByReference(); }
	protected ByValue newByValue() { return new ByValue(); }
	protected MonoObject newInstance() { return new MonoObject(); }
	public static MonoObject[] newArray(int arrayLength) {
		return com.ochafik.lang.jnaerator.runtime.Structure.newArray(MonoObject.class, arrayLength);
	}
	public static class ByReference extends MonoObject implements com.sun.jna.Structure.ByReference {}
	public static class ByValue extends MonoObject implements com.sun.jna.Structure.ByValue {}
}