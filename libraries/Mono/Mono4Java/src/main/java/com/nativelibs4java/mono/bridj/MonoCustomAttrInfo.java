package com.nativelibs4java.mono.bridj;
import com.nativelibs4java.mono.bridj.MonoLibrary.MonoImage;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * <i>native declaration : /Library/Frameworks/Mono.framework/Headers/mono-2.0/mono/metadata/reflection.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("mono") 
public class MonoCustomAttrInfo extends StructObject {
	public MonoCustomAttrInfo() {
		super();
	}
	public MonoCustomAttrInfo(Pointer pointer) {
		super(pointer);
	}
	@Field(0) 
	public int num_attrs() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public MonoCustomAttrInfo num_attrs(int num_attrs) {
		this.io.setIntField(this, 0, num_attrs);
		return this;
	}
	@Field(1) 
	public int cached() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public MonoCustomAttrInfo cached(int cached) {
		this.io.setIntField(this, 1, cached);
		return this;
	}
	/// C type : MonoImage*
	@Field(2) 
	public Pointer<MonoImage > image() {
		return this.io.getPointerField(this, 2);
	}
	/// C type : MonoImage*
	@Field(2) 
	public MonoCustomAttrInfo image(Pointer<MonoImage > image) {
		this.io.setPointerField(this, 2, image);
		return this;
	}
	/// C type : MonoCustomAttrEntry[0]
	@Array({0}) 
	@Field(3) 
	public Pointer<MonoCustomAttrEntry > attrs() {
		return this.io.getPointerField(this, 3);
	}
}