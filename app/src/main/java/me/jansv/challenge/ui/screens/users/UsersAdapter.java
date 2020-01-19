package me.jansv.challenge.ui.screens.users;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jansv.challenge.R;
import me.jansv.challenge.model.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.Holder>{

    public List<User> getUsers() {
        return users;
    }

    private List<User> users;



    private ListItemClick clicklistener;



    public UsersAdapter(List<User> users, ListItemClick listen)
    {
        this.users = users;

        clicklistener = listen;

    }

    public interface ListItemClick{
        public void onListItemClick(int clickedItem);
    }




    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.bind(users.get(i));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.user_name)
        TextView userName;

        @BindView(R.id.user_state)
        TextView userState;

        @BindView(R.id.profile_image)
        ImageView userImage;


        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        private void bind(User user) {
            userName.setText(user.getLogin());
            userState.setText("Lagos");
            Glide.with(itemView.getContext()).load(user.getAvatarUrl()).into(userImage);
        }


        @Override
        public void onClick(View v) {
            int clickeditem = getAdapterPosition();

            clicklistener.onListItemClick(clickeditem);
        }
    }
}
