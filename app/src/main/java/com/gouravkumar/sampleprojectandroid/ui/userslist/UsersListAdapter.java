package com.gouravkumar.sampleprojectandroid.ui.userslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gouravkumar.sampleprojectandroid.R;
import com.gouravkumar.sampleprojectandroid.model.UsersInfo;

import java.util.ArrayList;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserListViewHolder> {
    private Context context;
    private ArrayList<UsersInfo> usersArrayList;

    UsersListAdapter(Context context, ArrayList<UsersInfo> usersArrayList){
        this.context = context;
        this.usersArrayList = usersArrayList;
    }


    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_users_list, parent, false);
        return new UserListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {

     UsersInfo usersInfo = usersArrayList.get(position);
     String id = "User ID:" + (usersInfo.getId());
        Glide.with(context)
                .load(usersInfo.getAvatar_url())
                .placeholder(R.drawable.ic_broken_image)
                .into(holder.userImage);

        holder.userName.setText(usersInfo.getLogin());
        holder.userId.setText(id);
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public static class UserListViewHolder extends RecyclerView.ViewHolder{
        private AppCompatImageView userImage;
        private AppCompatTextView userName;
        private AppCompatTextView userId;

        UserListViewHolder(View itemView){
         super(itemView);

         userImage = itemView.findViewById(R.id.user_imgV);
         userId = itemView.findViewById(R.id.user_id_tv);
         userName = itemView.findViewById(R.id.user_name_tv);
        }

    }
}
