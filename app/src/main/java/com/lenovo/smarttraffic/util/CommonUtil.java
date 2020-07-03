package com.lenovo.smarttraffic.util;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.lenovo.smarttraffic.InitApp;
import com.lenovo.smarttraffic.R;



public class CommonUtil {

	private SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(InitApp.getInstance());

	private static final class CommonUtilInstance {
		private static final CommonUtil instance = new CommonUtil();
	}

	public static CommonUtil getInstance() {
		return CommonUtilInstance.instance;
	}
	/**
	 * 在主线程中运行
	 * @param r
	 */
	public static void runOnUIThread(Runnable r){
		InitApp.getHandler().post(r);
	}

	/**
	 * 获取resources对象
	 * @return
	 */
	public static Resources getResources(){
		return InitApp.getInstance().getResources();
	}

	/**
	 * 获取字符串
	 * @param resId
	 * @return
	 */
	public static String getString(int resId){
		return getResources().getString(resId);
	}

	/**
	 * 获取资源图片
	 * @param resId
	 * @return
	 */
	public static Drawable getDrawable(int resId){
		return getResources().getDrawable(resId);
	}

	/**
	 * 获取dimes值
	 * @param resId
	 * @return
	 */
	public static float getDimens(int resId){
		return getResources().getDimension(resId);
	}

	/**
	 * 获取字符串的数组
	 * @param resId
	 * @return
	 */
	public static String[] getStringArray(int resId){
		return getResources().getStringArray(resId);
	}


	/**
	 * 将自己从父容器中移除
	 */
	public static void removeSelfFromParent(View child) {
		// 获取父view
		if (child != null) {
			ViewParent parent = child.getParent();
			if (parent instanceof ViewGroup) {
				ViewGroup viewGroup = (ViewGroup) parent;
				// 将自己移除
				viewGroup.removeView(child);
			}
		}
	}

	/**
	 * 获取主题颜色
	 */
	public int getColor() {
		int defaultColor = InitApp.getInstance().getResources().getColor(R.color.colorPrimary);
		int color = setting.getInt("color", defaultColor);
		if ((color != 0) && Color.alpha(color) != 255) {
			return defaultColor;
		}
		return color;
	}

}