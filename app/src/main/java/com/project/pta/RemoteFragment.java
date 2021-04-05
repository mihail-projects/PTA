package com.project.pta;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RemoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RemoteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RemoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RemoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RemoteFragment newInstance(String param1, String param2) {
        RemoteFragment fragment = new RemoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_remote, container, false);
    }

    TextView matchDate;
    TextView matchCity;
    TextView matchCountry;
    TextView matchSport;

    ListView playersList;
    TextView newPlayer;

    int index;
    List<DocumentSnapshot> documents;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> list;

    int listPos;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if(view != null) {

            matchDate = view.findViewById(R.id.matchDate);
            matchCity = view.findViewById(R.id.matchCity);
            matchCountry = view.findViewById(R.id.matchCountry);
            matchSport = view.findViewById(R.id.matchSport);

            playersList = view.findViewById(R.id.matchPlayers);
            playersList.setOnItemClickListener((arg0, arg1, arg2, arg3) -> listPos = arg2);

            newPlayer = view.findViewById(R.id.newPlayer);

            list = new ArrayList<>(Arrays.asList(new String[0]));
            arrayAdapter = new ArrayAdapter(view.getContext(), R.layout.listitem, R.id.player, list);

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("matches").get().addOnCompleteListener(task -> {

                if (task.isSuccessful()) {

                    documents = task.getResult().getDocuments();

                    if(documents != null){

                        matchDate.setText(documents.get(0).getString("name"));
                        matchCity.setText(documents.get(0).getString("city"));
                        matchCountry.setText(documents.get(0).getString("country"));
                        matchSport.setText(documents.get(0).getString("sport"));

                        String playersLine = documents.get(0).getString("players");
                        String[] players = playersLine.split("-");

                        for(int i = 0; i < players.length; i++) {
                            arrayAdapter.add(players[i]);
                        }

                        playersList.setAdapter(arrayAdapter);

                    }

                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }

            });

            /*File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/pta1-309707-e36314cfcb29.json");


            GoogleCredentials credentials = null;

            try {
                credentials = GoogleCredentials.fromStream(new FileInputStream(file)).createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));;
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Use the application default credentials
            FirebaseOptions options = null;
            options = FirebaseOptions.builder().setCredentials(credentials).setProjectId("pta1-f8c21").build();

            FirebaseApp.initializeApp(options);
            Firestore db = FirestoreClient.getFirestore();


            // asynchronously retrieve all users
            ApiFuture<QuerySnapshot> query = db.collection("matches").get();

            // query.get() blocks on response
            QuerySnapshot querySnapshot = null;
            try {
                querySnapshot = query.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            view.findViewById(R.id.addPlayer).setOnClickListener(v -> {

                if(!newPlayer.getText().toString().isEmpty()) {
                    arrayAdapter.add(newPlayer.getText().toString());
                    playersList.setAdapter(arrayAdapter);
                }

            });

            view.findViewById(R.id.removePlayer).setOnClickListener(v -> {
                arrayAdapter.remove(playersList.getAdapter().getItem(listPos).toString());
                playersList.setAdapter(arrayAdapter);
            });

            view.findViewById(R.id.update).setOnClickListener(v -> {

                if(!matchDate.getText().toString().isEmpty() && !matchCity.getText().toString().isEmpty() && !matchCountry.getText().toString().isEmpty() && !matchSport.getText().toString().isEmpty() && playersList.getCount() > 0){

                    /*DocumentReference docRef = db.collection("matches").document(String.valueOf(Math.random()));

                    Map<String, Object> data = new HashMap<>();
                    data.put("date", matchDate.getText().toString());
                    data.put("city", matchCity.getText().toString());
                    data.put("country", matchCountry.getText().toString());
                    data.put("sport", matchSport.getText().toString());
                    
                    String players = null;
                    
                    for(int i = 0; i < playersList.getCount(); i++){
                        players += playersList.getAdapter().getItem(i) + "-";
                    }

                    data.put("players", players);

                    ApiFuture<WriteResult> result = docRef.set(data);*/

                    Map<String, Object> data = new HashMap<>();
                    data.put("date", matchDate.getText().toString());
                    data.put("city", matchCity.getText().toString());
                    data.put("country", matchCountry.getText().toString());
                    data.put("sport", matchSport.getText().toString());

                    String players = "";

                    for(int i = 0; i < playersList.getCount(); i++){
                        players += playersList.getAdapter().getItem(i) + "-";
                    }

                    data.put("players", players);

                    db.collection("matches").add(data)
                            .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                            .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

                }

            });

            view.findViewById(R.id.matchesNew).setOnClickListener(v -> {

                matchDate.setText("");
                matchCity.setText("");
                matchCountry.setText("");
                matchSport.setText("");
                playersList.setAdapter(null);

            });

            view.findViewById(R.id.matchesRemove).setOnClickListener(v -> {

                if(documents.size() > 0){

                    db.collection("matches").document(documents.get(listPos).getId()).delete();

                    matchDate.setText("");
                    matchCity.setText("");
                    matchCountry.setText("");
                    matchSport.setText("");
                    playersList.setAdapter(null);

                }

            });

            getView().findViewById(R.id.matchesPrev).setOnClickListener(v -> {

                if (documents.size() > 0) {

                    if (index == documents.size() - 1) {
                        index = 0;
                    } else {
                        index++;
                    }

                    matchDate.setText(documents.get(index).getString("name"));
                    matchCity.setText(documents.get(index).getString("city"));
                    matchCountry.setText(documents.get(index).getString("country"));
                    matchSport.setText(documents.get(index).getString("sport"));

                    String playersLine = documents.get(index).getString("players");
                    String[] players = playersLine.split("-");

                    list = new ArrayList<>(Arrays.asList(new String[0]));
                    arrayAdapter = new ArrayAdapter(view.getContext(), R.layout.listitem, R.id.player, players);
                    playersList.setAdapter(arrayAdapter);

                }

            });

            view.findViewById(R.id.matchesNext).setOnClickListener(v -> {

                if (documents.size() > 0) {

                    if (index == 0) {
                        index = documents.size() - 1;
                    } else {
                        index--;
                    }

                    matchDate.setText(documents.get(index).getString("name"));
                    matchCity.setText(documents.get(index).getString("city"));
                    matchCountry.setText(documents.get(index).getString("country"));
                    matchSport.setText(documents.get(index).getString("sport"));

                    String playersLine = documents.get(index).getString("players");
                    String[] players = playersLine.split("-");

                    list = new ArrayList<>(Arrays.asList(new String[0]));
                    arrayAdapter = new ArrayAdapter(view.getContext(), R.layout.listitem, R.id.player, players);
                    playersList.setAdapter(arrayAdapter);

                }

            });

        }

    }

}