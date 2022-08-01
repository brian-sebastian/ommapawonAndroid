package com.example.otp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otp.R;
import com.example.otp.models.Menu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder>{
    private Context mContext;
    private List<Menu> menuList;
    FragmentManager fragmentManager;
    private AdapterView.OnItemClickListener listener;
    String path;
    MyViewHolder holder2;

    public MenuAdapter(Context mContext, List<Menu> menuList, AdapterView.OnItemClickListener listener) {
        super();
        this.mContext = mContext;
        this.menuList = menuList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_list_menu, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Menu data = menuList.get(position);
        holder.mNamaMenu.setText(data.getNama_makanan());
        holder.mImageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
//                View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_image_zoom, null);
//                PhotoView photoView = mView.findViewById(R.id.imageView);
//                Picasso.get().load(path + data.getMenuFoto()).into(photoView);
//                //photoView.setImageResource(R.drawable.nature);
//                mBuilder.setView(mView);
//                AlertDialog mDialog = mBuilder.create();
//                mDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvNamaMenu)
        TextView mNamaMenu;
        @BindView(R.id.tvHargaMenu)
        TextView mHargaMenu;
        @BindView(R.id.parentLayout)
        CardView mParentLayout;
        @BindView(R.id.tvKetersedian)
        TextView mKetersediaan;
        @BindView(R.id.imgLove)
        ImageView mLove;
        @BindView(R.id.imageMenu)
        ImageView mImageMenu;
        @BindView(R.id.tvJmlFavorit)
        TextView mJmlFavorit;
        @BindView(R.id.imgLoveBlack)
        ImageView mLoveBlack;
        @BindView(R.id.tvHargaCoret)
        TextView mHargaCoret;
        @BindView(R.id.tvDiscount)
        TextView mDiscount;
        @BindView(R.id.layoutDiscount)
        LinearLayout layoutDiscount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
