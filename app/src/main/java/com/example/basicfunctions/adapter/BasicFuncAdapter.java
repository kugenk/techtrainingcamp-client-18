package com.example.basicfunctions.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.basicfunctions.R;
import com.example.basicfunctions.entity.MetaData;
import java.util.List;

public class BasicFuncAdapter extends BaseAdapter {

    private List<MetaData> metaDataList;
    private Context mContext;

    public BasicFuncAdapter(List<MetaData> metaDataList, Context context) {
        this.metaDataList = metaDataList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return metaDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return metaDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView是缓存布局，可以重用，提高ListView运行效率
        BasicFuncAdapter.ViewHolder0 viewHolder0 = null;
        BasicFuncAdapter.ViewHolder1 viewHolder1 = null;
        BasicFuncAdapter.ViewHolder2 viewHolder2 = null;
        BasicFuncAdapter.ViewHolder3 viewHolder3 = null;
        BasicFuncAdapter.ViewHolder4 viewHolder4 = null;
        if (convertView == null) {
            switch (metaDataList.get(position).getType()) {
                case 0:
                    viewHolder0 = new BasicFuncAdapter.ViewHolder0();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.type0, parent, false);
                    viewHolder0.title = convertView.findViewById(R.id.title);
                    viewHolder0.author = convertView.findViewById(R.id.author);
                    viewHolder0.publishTime = convertView.findViewById(R.id.publishTime);
                    break;
                case 1:
                    viewHolder1 = new BasicFuncAdapter.ViewHolder1();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.type1, parent, false);
                    viewHolder1.title = convertView.findViewById(R.id.title);
                    viewHolder1.author = convertView.findViewById(R.id.author);
                    viewHolder1.publishTime = convertView.findViewById(R.id.publishTime);
                    viewHolder1.imageView = convertView.findViewById(R.id.image);
                    break;
                case 2:
                    viewHolder2 = new BasicFuncAdapter.ViewHolder2();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.type2, parent, false);
                    viewHolder2.title = convertView.findViewById(R.id.title);
                    viewHolder2.author = convertView.findViewById(R.id.author);
                    viewHolder2.publishTime = convertView.findViewById(R.id.publishTime);
                    viewHolder2.imageView = convertView.findViewById(R.id.image);
                    break;
                case 3:
                    viewHolder3 = new BasicFuncAdapter.ViewHolder3();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.type3, parent, false);
                    viewHolder3.title = convertView.findViewById(R.id.title);
                    viewHolder3.author = convertView.findViewById(R.id.author);
                    viewHolder3.publishTime = convertView.findViewById(R.id.publishTime);
                    viewHolder3.imageView = convertView.findViewById(R.id.image);
                    break;
                case 4:
                    viewHolder4 = new BasicFuncAdapter.ViewHolder4();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.type4, parent, false);
                    viewHolder4.title = convertView.findViewById(R.id.title);
                    viewHolder4.author = convertView.findViewById(R.id.author);
                    viewHolder4.publishTime = convertView.findViewById(R.id.publishTime);
                    viewHolder4.imageView1 = convertView.findViewById(R.id.image1);
                    viewHolder4.imageView2 = convertView.findViewById(R.id.image2);
                    viewHolder4.imageView3 = convertView.findViewById(R.id.image3);
                    viewHolder4.imageView4 = convertView.findViewById(R.id.image4);
                    break;
                default:
            }
        } else {
            switch (metaDataList.get(position).getType()) {
                case 0:
                    viewHolder0 = (BasicFuncAdapter.ViewHolder0) convertView.getTag();
                    break;
                case 1:
                    viewHolder1 = (BasicFuncAdapter.ViewHolder1) convertView.getTag();
                    break;
                case 2:
                    viewHolder2 = (BasicFuncAdapter.ViewHolder2) convertView.getTag();
                    break;
                case 3:
                    viewHolder3 = (BasicFuncAdapter.ViewHolder3) convertView.getTag();
                    break;
                case 4:
                    viewHolder4 = (BasicFuncAdapter.ViewHolder4) convertView.getTag();
                    break;
                default:
            }
        }
        switch (metaDataList.get(position).getType()) {
            case 0:
                viewHolder0.title.setText(metaDataList.get(position).getTitle());
                viewHolder0.author.setText(metaDataList.get(position).getAuthor());
                viewHolder0.publishTime.setText(metaDataList.get(position).getPublishTime());
                break;
            case 1:
                int imgCase1 = mContext.getResources().getIdentifier(removeSuffix(metaDataList.get(position).getCover()), "mipmap", mContext.getPackageName());
                viewHolder1.title.setText(metaDataList.get(position).getTitle());
                viewHolder1.author.setText(metaDataList.get(position).getAuthor());
                viewHolder1.publishTime.setText(metaDataList.get(position).getPublishTime());
                viewHolder1.imageView.setImageResource(imgCase1);
                break;
            case 2:
                int imgCase2 = mContext.getResources().getIdentifier(removeSuffix(metaDataList.get(position).getCover()), "mipmap", mContext.getPackageName());
                viewHolder2.title.setText(metaDataList.get(position).getTitle());
                viewHolder2.author.setText(metaDataList.get(position).getAuthor());
                viewHolder2.publishTime.setText(metaDataList.get(position).getPublishTime());
                viewHolder2.imageView.setImageResource(imgCase2);
                break;
            case 3:
                int imgCase3 = mContext.getResources().getIdentifier(removeSuffix(metaDataList.get(position).getCover()), "mipmap", mContext.getPackageName());
                viewHolder3.title.setText(metaDataList.get(position).getTitle());
                viewHolder3.author.setText(metaDataList.get(position).getAuthor());
                viewHolder3.publishTime.setText(metaDataList.get(position).getPublishTime());
                viewHolder3.imageView.setImageResource(imgCase3);
                break;
            case 4:
                int img1 = mContext.getResources().getIdentifier(removeSuffix(metaDataList.get(position).getCovers()[0]), "mipmap", mContext.getPackageName());
                int img2 = mContext.getResources().getIdentifier(removeSuffix(metaDataList.get(position).getCovers()[1]), "mipmap", mContext.getPackageName());
                int img3 = mContext.getResources().getIdentifier(removeSuffix(metaDataList.get(position).getCovers()[2]), "mipmap", mContext.getPackageName());
                int img4 = mContext.getResources().getIdentifier(removeSuffix(metaDataList.get(position).getCovers()[3]), "mipmap", mContext.getPackageName());
                viewHolder4.title.setText(metaDataList.get(position).getTitle());
                viewHolder4.author.setText(metaDataList.get(position).getAuthor());
                viewHolder4.publishTime.setText(metaDataList.get(position).getPublishTime());
                viewHolder4.imageView1.setImageResource(img1);
                viewHolder4.imageView2.setImageResource(img2);
                viewHolder4.imageView3.setImageResource(img3);
                viewHolder4.imageView4.setImageResource(img4);
                break;
            default:
        }
        return convertView;
    }

    private String removeSuffix(String pre) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < pre.length(); i++) {
            if (pre.charAt(i) == '.') {
                break;
            }
            stringBuilder.append(pre.charAt(i));
        }
        return stringBuilder.toString();
    }

    static class ViewHolder0 {
        TextView title;
        TextView author;
        TextView publishTime;
    }

    static class ViewHolder1 {
        ImageView imageView;
        TextView title;
        TextView author;
        TextView publishTime;
    }

    static class ViewHolder2 {
        ImageView imageView;
        TextView title;
        TextView author;
        TextView publishTime;
    }

    static class ViewHolder3 {
        ImageView imageView;
        TextView title;
        TextView author;
        TextView publishTime;
    }

    static class ViewHolder4 {
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;
        TextView title;
        TextView author;
        TextView publishTime;
    }
}
