package com.example.notification;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notification.room.NotifViewModel;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ListNotification extends Fragment {
    private LiveData<List<NotificationISeeNero>> mAllTugas;
    public static final int NEW_NOTIF_ACTIVITY_REQUEST_CODE = 1;
    private NotifViewModel notifViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.histori_notification, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_notif);
        NotificationAdapter adapter = new NotificationAdapter(new NotificationAdapter.WordDiff(),getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        notifViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(NotifViewModel.class);
        notifViewModel.getAllNotif().observe(getViewLifecycleOwner(),ListNotif ->{
            adapter.submitList(ListNotif);
        } );
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == NEW_NOTIF_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            NotificationISeeNero notif = new NotificationISeeNero(data.getStringExtra(data.getStringExtra("namaSungai"))
//                    ,data.getStringExtra("tingkatBahaya")
//                    ,data.getStringExtra("waktu")
//                    ,data.getStringExtra("tandaSeru")
//
//                   );
//            notifViewModel.update(notif);
//        }
    }


}
