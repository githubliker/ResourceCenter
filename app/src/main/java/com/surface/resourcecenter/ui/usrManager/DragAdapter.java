/*
 *  http://www.appcodes.cn APP精品源码下载站声明：
 * 1、本站源码为网上搜集或网友提供，如果涉及或侵害到您的版 权，请立即通知我们。
 * 2、 本站提供免费代码只可供研究学习使用，切勿用于商业用途 由此引起一切后果与本站无关。
 * 3、 商业源码请在源码授权范围内进行使用。
 * 4、更多APP精品源码下载请访问:http://www.appcodes.cn。
 * 5、如有疑问请发信息至appcodes@qq.com。
 */
package com.surface.resourcecenter.ui.usrManager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.usrManager.bean.ChannelItem;

import java.util.ArrayList;
import java.util.List;

public class DragAdapter extends BaseAdapter {
	/** TAG*/
	private final static String TAG = "DragAdapter";
	/** 是否显示底部的ITEM */
	private boolean isItemShow = false;
	private Context context;
	/** 控制的postion */
	private int holdPosition;
	/** 是否改变 */
	private boolean isChanged = false;
	/** 列表数据是否改变 */
	private boolean isListChanged = false;
	/** 是否可见 */
	boolean isVisible = true;
	/** 可以拖动的列表（即用户选择的频道列表） */
	public List<ChannelItem> channelList;
	/** TextView 频道内容 */
	private TextView item_text;
	private ImageView item_delete;
	private View role;
	/** 要删除的position */
	public int remove_position = -1;

	public DragAdapter(Context context, List<ChannelItem> channelList) {
		this.context = context;
		this.channelList = channelList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return channelList == null ? 0 : channelList.size();
	}

	@Override
	public ChannelItem getItem(int position) {
		// TODO Auto-generated method stub
		if (channelList != null && channelList.size() != 0) {
			return channelList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.rc_fragment_manager_usr_item, null);
		item_text = (TextView) view.findViewById(R.id.text_item);
		item_delete = (ImageView) view.findViewById(R.id.icon_delete);
		role = view.findViewById(R.id.text_role);
		ChannelItem channel = getItem(position);
		item_text.setText(channel.getName());
		if("收样员".equals(channel.getRole())){
			role.setBackgroundResource(R.color.holo_blue_bright);
		} else if("调度员".equals(channel.getRole())){
			role.setBackgroundResource(R.color.holo_green_dark);
		}else if("试验员".equals(channel.getRole())){
			role.setBackgroundResource(R.color.holo_orange_light);
		}else if("编制".equals(channel.getRole())){
			role.setBackgroundResource(R.color.holo_purple);
		}else if("审核".equals(channel.getRole())){
			role.setBackgroundResource(R.color.holo_red_dark);
		}else if("终审".equals(channel.getRole())){
			role.setBackgroundResource(R.color.holo_red_light);
		}else if("未分配".equals(channel.getRole())){
			role.setBackgroundResource(R.color.holo_gray_light);
		} else {
			role.setBackgroundResource(R.color.holo_gray_light);
		}
		if("delete".equals(channel.getMode())){
			item_delete.setVisibility(View.VISIBLE);
		} else {
			item_delete.setVisibility(View.INVISIBLE);
		}
//		if (position == 0){
//			item_text.setTextColor(context.getResources().getColor(R.color.maincolor));
//			item_text.setEnabled(false);
//		}
		if (isChanged && (position == holdPosition) && !isItemShow) {
			item_text.setText("");
			item_text.setSelected(true);
			item_text.setEnabled(true);
			isChanged = false;
		}
		if (!isVisible && (position == -1 + channelList.size())) {
			item_text.setText("");
			item_text.setSelected(true);
			item_text.setEnabled(true);
		}
		if(remove_position == position){
			item_text.setText("");
		}
		return view;
	}

	/** 添加频道列表 */
	public void addItem(ChannelItem channel) {
		channelList.add(channel);
		isListChanged = true;
		notifyDataSetChanged();
	}

	/** 拖动变更频道排序 */
	public void exchange(int dragPostion, int dropPostion) {
		holdPosition = dropPostion;
		ChannelItem dragItem = getItem(dragPostion);
		Log.d(TAG, "startPostion=" + dragPostion + ";endPosition=" + dropPostion);
		if (dragPostion < dropPostion) {
			channelList.add(dropPostion + 1, dragItem);
			channelList.remove(dragPostion);
		} else {
			channelList.add(dropPostion, dragItem);
			channelList.remove(dragPostion + 1);
		}
		isChanged = true;
		isListChanged = true;
		notifyDataSetChanged();
	}

	/** 获取频道列表 */
	public List<ChannelItem> getChannnelLst() {
		if (channelList == null) {
			channelList = new ArrayList<>();
		}
		return channelList;
	}

	/** 设置删除的position */
	public void setRemove(int position) {
		remove_position = position;
		notifyDataSetChanged();
	}

	/** 删除频道列表 */
	public void remove() {
		channelList.remove(remove_position);
		remove_position = -1;
		isListChanged = true;
		notifyDataSetChanged();
	}

	/** 设置频道列表 */
	public void setListDate(List<ChannelItem> list) {
		channelList = list;
	}

	/** 获取是否可见 */
	public boolean isVisible() {
		return isVisible;
	}

	/** 排序是否发生改变 */
	public boolean isListChanged() {
		return isListChanged;
	}

	/** 设置是否可见 */
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	/** 显示放下的ITEM */
	public void setShowDropItem(boolean show) {
		isItemShow = show;
	}
}