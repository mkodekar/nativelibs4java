package com.nativelibs4java.ffmpeg.avformat;
import com.nativelibs4java.ffmpeg.avcodec.AvcodecLibrary.CodecID;
import com.nativelibs4java.ffmpeg.avutil.AVRational;
import com.nativelibs4java.ffmpeg.avutil.AvutilLibrary.PixelFormat;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ValuedEnum;
import org.bridj.ann.Bits;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * <i>native declaration : libavformat/avformat.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("avformat") 
public class AVFormatParameters extends StructObject {
	public AVFormatParameters() {
		super();
	}
	public AVFormatParameters(Pointer pointer) {
		super(pointer);
	}
	/// C type : AVRational
	@Field(0) 
	public AVRational time_base() {
		return this.io.getNativeObjectField(this, 0);
	}
	@Field(1) 
	public int sample_rate() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public AVFormatParameters sample_rate(int sample_rate) {
		this.io.setIntField(this, 1, sample_rate);
		return this;
	}
	public final int sample_rate_$eq(int sample_rate) {
		sample_rate(sample_rate);
		return sample_rate;
	}
	@Field(2) 
	public int channels() {
		return this.io.getIntField(this, 2);
	}
	@Field(2) 
	public AVFormatParameters channels(int channels) {
		this.io.setIntField(this, 2, channels);
		return this;
	}
	public final int channels_$eq(int channels) {
		channels(channels);
		return channels;
	}
	@Field(3) 
	public int width() {
		return this.io.getIntField(this, 3);
	}
	@Field(3) 
	public AVFormatParameters width(int width) {
		this.io.setIntField(this, 3, width);
		return this;
	}
	public final int width_$eq(int width) {
		width(width);
		return width;
	}
	@Field(4) 
	public int height() {
		return this.io.getIntField(this, 4);
	}
	@Field(4) 
	public AVFormatParameters height(int height) {
		this.io.setIntField(this, 4, height);
		return this;
	}
	public final int height_$eq(int height) {
		height(height);
		return height;
	}
	/// C type : PixelFormat
	@Field(5) 
	public ValuedEnum<PixelFormat > pix_fmt() {
		return this.io.getEnumField(this, 5);
	}
	/// C type : PixelFormat
	@Field(5) 
	public AVFormatParameters pix_fmt(ValuedEnum<PixelFormat > pix_fmt) {
		this.io.setEnumField(this, 5, pix_fmt);
		return this;
	}
	/// C type : PixelFormat
	public final ValuedEnum<PixelFormat > pix_fmt_$eq(ValuedEnum<PixelFormat > pix_fmt) {
		pix_fmt(pix_fmt);
		return pix_fmt;
	}
	/// < Used to select DV channel.
	@Field(6) 
	public int channel() {
		return this.io.getIntField(this, 6);
	}
	/// < Used to select DV channel.
	@Field(6) 
	public AVFormatParameters channel(int channel) {
		this.io.setIntField(this, 6, channel);
		return this;
	}
	public final int channel_$eq(int channel) {
		channel(channel);
		return channel;
	}
	/**
	 * < TV standard, NTSC, PAL, SECAM<br>
	 * C type : const char*
	 */
	@Field(7) 
	public Pointer<Byte > standard() {
		return this.io.getPointerField(this, 7);
	}
	/**
	 * < TV standard, NTSC, PAL, SECAM<br>
	 * C type : const char*
	 */
	@Field(7) 
	public AVFormatParameters standard(Pointer<Byte > standard) {
		this.io.setPointerField(this, 7, standard);
		return this;
	}
	/// C type : const char*
	public final Pointer<Byte > standard_$eq(Pointer<Byte > standard) {
		standard(standard);
		return standard;
	}
	/// < Force raw MPEG-2 transport stream output, if possible.
	@Field(8) 
	@Bits(1) 
	public int mpeg2ts_raw() {
		return this.io.getIntField(this, 8);
	}
	/// < Force raw MPEG-2 transport stream output, if possible.
	@Field(8) 
	@Bits(1) 
	public AVFormatParameters mpeg2ts_raw(int mpeg2ts_raw) {
		this.io.setIntField(this, 8, mpeg2ts_raw);
		return this;
	}
	@Bits(1) 
	public final int mpeg2ts_raw_$eq(int mpeg2ts_raw) {
		mpeg2ts_raw(mpeg2ts_raw);
		return mpeg2ts_raw;
	}
	/**
	 * < Compute exact PCR for each transport<br>
	 * stream packet (only meaningful if<br>
	 * mpeg2ts_raw is TRUE).
	 */
	@Field(9) 
	@Bits(1) 
	public int mpeg2ts_compute_pcr() {
		return this.io.getIntField(this, 9);
	}
	/**
	 * < Compute exact PCR for each transport<br>
	 * stream packet (only meaningful if<br>
	 * mpeg2ts_raw is TRUE).
	 */
	@Field(9) 
	@Bits(1) 
	public AVFormatParameters mpeg2ts_compute_pcr(int mpeg2ts_compute_pcr) {
		this.io.setIntField(this, 9, mpeg2ts_compute_pcr);
		return this;
	}
	@Bits(1) 
	public final int mpeg2ts_compute_pcr_$eq(int mpeg2ts_compute_pcr) {
		mpeg2ts_compute_pcr(mpeg2ts_compute_pcr);
		return mpeg2ts_compute_pcr;
	}
	/**
	 * < Do not begin to play the stream<br>
	 * immediately (RTSP only).
	 */
	@Field(10) 
	@Bits(1) 
	public int initial_pause() {
		return this.io.getIntField(this, 10);
	}
	/**
	 * < Do not begin to play the stream<br>
	 * immediately (RTSP only).
	 */
	@Field(10) 
	@Bits(1) 
	public AVFormatParameters initial_pause(int initial_pause) {
		this.io.setIntField(this, 10, initial_pause);
		return this;
	}
	@Bits(1) 
	public final int initial_pause_$eq(int initial_pause) {
		initial_pause(initial_pause);
		return initial_pause;
	}
	@Field(11) 
	@Bits(1) 
	public int prealloced_context() {
		return this.io.getIntField(this, 11);
	}
	@Field(11) 
	@Bits(1) 
	public AVFormatParameters prealloced_context(int prealloced_context) {
		this.io.setIntField(this, 11, prealloced_context);
		return this;
	}
	@Bits(1) 
	public final int prealloced_context_$eq(int prealloced_context) {
		prealloced_context(prealloced_context);
		return prealloced_context;
	}
	/// C type : CodecID
	@Field(12) 
	public ValuedEnum<CodecID > video_codec_id() {
		return this.io.getEnumField(this, 12);
	}
	/// C type : CodecID
	@Field(12) 
	public AVFormatParameters video_codec_id(ValuedEnum<CodecID > video_codec_id) {
		this.io.setEnumField(this, 12, video_codec_id);
		return this;
	}
	/// C type : CodecID
	public final ValuedEnum<CodecID > video_codec_id_$eq(ValuedEnum<CodecID > video_codec_id) {
		video_codec_id(video_codec_id);
		return video_codec_id;
	}
	/// C type : CodecID
	@Field(13) 
	public ValuedEnum<CodecID > audio_codec_id() {
		return this.io.getEnumField(this, 13);
	}
	/// C type : CodecID
	@Field(13) 
	public AVFormatParameters audio_codec_id(ValuedEnum<CodecID > audio_codec_id) {
		this.io.setEnumField(this, 13, audio_codec_id);
		return this;
	}
	/// C type : CodecID
	public final ValuedEnum<CodecID > audio_codec_id_$eq(ValuedEnum<CodecID > audio_codec_id) {
		audio_codec_id(audio_codec_id);
		return audio_codec_id;
	}
}
