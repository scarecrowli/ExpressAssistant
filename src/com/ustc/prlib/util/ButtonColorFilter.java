package com.ustc.prlib.util;

import android.graphics.ColorMatrixColorFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;

/**
 * @description : �Ե���İ�ť�Ƚ�����ɫ����,ʵ�ֵ��Ч�� ���÷���:public final static void
 *              setButtonFocusChanged(View inView)
 * @package cn.apppark.mcd.util
 * @title:ButtonColorFilter.java
 * @author : email:xiangyanhui@unitepower.net
 * @date :2012-5-4 ����02:23:23
 * @version : v1.0
 */
public class ButtonColorFilter {

	// ���������ť���е���ɫ����
	public final static float[] BT_SELECTED = new float[] { 1, 0, 0, 0, 50, 0,
			1, 0, 0, 50, 0, 0, 1, 0, 50, 0, 0, 0, 1, 0 };

	// ��ť�ָ�ԭ״����ɫ����
	public final static float[] BT_NOT_SELECTED = new float[] { 1, 0, 0, 0, 0,
			0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };

	private static int initAlpha = 255;
	private static int clickAlpha = 200;

	/**
	 * ��ť����ı�
	 */
	private final static OnFocusChangeListener buttonOnFocusChangeListener = new OnFocusChangeListener() {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if (hasFocus) {
				v.getBackground().setColorFilter(
						new ColorMatrixColorFilter(BT_SELECTED));
				v.setBackgroundDrawable(v.getBackground());
			} else {
				v.getBackground().setColorFilter(
						new ColorMatrixColorFilter(BT_NOT_SELECTED));
				v.setBackgroundDrawable(v.getBackground());
			}
		}
	};

	/**
	 * ��ť��������Ч��(��ͼƬ)
	 */
	private final static OnTouchListener buttonOnTouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				v.getBackground().setColorFilter(
						new ColorMatrixColorFilter(BT_SELECTED));
				v.setBackgroundDrawable(v.getBackground());
			} else {
				v.getBackground().setColorFilter(
						new ColorMatrixColorFilter(BT_NOT_SELECTED));
				v.setBackgroundDrawable(v.getBackground());
			}
			return false;
		}
	};

	/**
	 * ��ť��������Ч��(��ɫ)
	 */
	private final static OnTouchListener buttonColorOnTouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				v.getBackground().setColorFilter(
						new ColorMatrixColorFilter(BT_SELECTED));
				v.getBackground().setAlpha(clickAlpha);
				v.setBackgroundDrawable(v.getBackground());
			} else {
				v.getBackground().setAlpha(initAlpha);
				v.getBackground().setColorFilter(
						new ColorMatrixColorFilter(BT_NOT_SELECTED));
				v.setBackgroundDrawable(v.getBackground());
			}
			return false;
		}
	};

	/**
	 * ����ͼƬ��ť��ȡ����ı�״̬
	 */
	public final static void setButtonFocusChanged(View inView) {
		inView.setOnTouchListener(buttonOnTouchListener);
		inView.setOnFocusChangeListener(buttonOnFocusChangeListener);
	}

	/**
	 * ����ͼƬ��ť��ȡ����ı�״̬(��������ͼƬʱ)
	 * 
	 * @param inImageButton
	 * @param initalpha
	 *            ��ʼ͸����,���ڵ����ԭ
	 */
	public final static void setButtonFocusChanged(View inView, int initalpha) {
		ButtonColorFilter.initAlpha = initalpha;
		inView.setOnTouchListener(buttonOnTouchListener);
		inView.setOnFocusChangeListener(buttonOnFocusChangeListener);
	}

	/**
	 * ����ͼƬ��ť��ȡ����ı�״̬ (����������ɫʱ)
	 * 
	 * @param inImageButton
	 * @param initalpha
	 *            ��ʼ͸����,���ڵ����ԭ
	 */
	public final static void setButtonColorFocusChanged(View inView,
			int initalpha) {
		ButtonColorFilter.initAlpha = initalpha;
		inView.setOnTouchListener(buttonColorOnTouchListener);
		inView.setOnFocusChangeListener(buttonOnFocusChangeListener);
	}

}
