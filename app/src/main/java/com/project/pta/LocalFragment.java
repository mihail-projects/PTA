package com.project.pta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LocalFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Local.
     */
    // TODO: Rename and change types and number of parameters
    public static LocalFragment newInstance(String param1, String param2) {
        LocalFragment fragment = new LocalFragment();
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
        return inflater.inflate(R.layout.fragment_local, container, false);
    }


    int sportIndex, teamIndex, athleteIndex = 0;

    TextView sportName;
    TextView sportType;
    TextView sportGender;

    TextView athleteName;
    TextView athleteLastname;
    TextView athleteCity;
    TextView athleteCountry;
    TextView athleteSportCode;
    TextView athleteBirthYear;

    TextView teamName;
    TextView teamStadium;
    TextView teamCity;
    TextView teamCountry;
    TextView teamSportCode;
    TextView teamFoundingYear;

    SportDAO sportDAO;
    TeamDAO teamDAO;
    AthleteDAO athleteDAO;

    List<Sport> sports;
    List<Team> teams;
    List<Athlete> athletes;

    boolean sNew = false;
    boolean aNew = false;
    boolean tNew = false;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if(view != null) {

            sportName = view.findViewById(R.id.sportName);
            sportType = view.findViewById(R.id.sportType);
            sportGender = view.findViewById(R.id.sportGender);

            athleteName = view.findViewById(R.id.matchDate);
            athleteLastname = view.findViewById(R.id.matchCity);
            athleteCity = view.findViewById(R.id.matchCountry);
            athleteCountry = view.findViewById(R.id.matchSport);
            athleteSportCode = view.findViewById(R.id.athleteSportCode);
            athleteBirthYear = view.findViewById(R.id.athleteBirth);

            teamName = view.findViewById(R.id.teamName);
            teamStadium = view.findViewById(R.id.teamStadium);
            teamCity = view.findViewById(R.id.teamCity);
            teamCountry = view.findViewById(R.id.teamCountry);
            teamSportCode = view.findViewById(R.id.teamSportCode);
            teamFoundingYear = view.findViewById(R.id.teamFounding);

            AppDatabase db = Room.databaseBuilder(view.getContext(), AppDatabase.class, "database").allowMainThreadQueries().build();

            sportDAO = db.sportDAO();
            sports = sportDAO.getAll();

            teamDAO = db.teamDAO();
            teams = teamDAO.getAll();

            athleteDAO = db.athleteDAO();
            athletes = athleteDAO.getAll();

            if(sports.size() > 0){
                set(0);
            }
            if(athletes.size() > 0){
                set(1);
            }
            if(teams.size() > 0){
                set(2);
            }

            //Updates automatically
            view.findViewById(R.id.update).setOnClickListener(v -> {

                if(!sportName.getText().toString().isEmpty() && !sportType.getText().toString().isEmpty() && !sportGender.getText().toString().isEmpty()){

                    if(sNew || sports.size() == 0) {
                        sportDAO.insert(new Sport(0, sportName.getText().toString(), sportType.getText().toString(), sportGender.getText().toString()));
                        Toast.makeText(view.getContext(), "Added Sport", Toast.LENGTH_SHORT).show();
                        sNew = false;
                    }else{
                        sportDAO.update(new Sport(sports.get(sportIndex).sid, sportName.getText().toString(), sportType.getText().toString(), sportGender.getText().toString()));
                        Toast.makeText(view.getContext(), "Updated Sport", Toast.LENGTH_SHORT).show();
                    }

                    sports = sportDAO.getAll();

                }

                if(!athleteName.getText().toString().isEmpty() && !athleteLastname.getText().toString().isEmpty() && !athleteCity.getText().toString().isEmpty() && !athleteCountry.getText().toString().isEmpty() && !athleteSportCode.getText().toString().isEmpty() && !athleteBirthYear.getText().toString().isEmpty()){

                    if(Integer.parseInt(athleteSportCode.getText().toString()) > sports.size()-1){
                        Toast.makeText(view.getContext(),"Sport doesn't exist",Toast.LENGTH_SHORT).show();
                    }else {

                        if(aNew || athletes.size() == 0) {
                            athleteDAO.insert(new Athlete(0, athleteName.getText().toString(), athleteLastname.getText().toString(), athleteCity.getText().toString(), athleteCountry.getText().toString(), Integer.parseInt(athleteSportCode.getText().toString()), Integer.parseInt(athleteBirthYear.getText().toString())));
                            Toast.makeText(view.getContext(), "Added Athlete", Toast.LENGTH_SHORT).show();
                            aNew = false;
                        }else{
                            athleteDAO.update(new Athlete(athletes.get(athleteIndex).aid, athleteName.getText().toString(), athleteLastname.getText().toString(), athleteCity.getText().toString(), athleteCountry.getText().toString(), Integer.parseInt(athleteSportCode.getText().toString()), Integer.parseInt(athleteBirthYear.getText().toString())));
                            Toast.makeText(view.getContext(), "Updated Athlete", Toast.LENGTH_SHORT).show();
                        }

                        athletes = athleteDAO.getAll();

                    }

                }

                if(!teamName.getText().toString().isEmpty() && !teamStadium.getText().toString().isEmpty() && !teamCity.getText().toString().isEmpty() && !teamCountry.getText().toString().isEmpty() && !teamSportCode.getText().toString().isEmpty() && !teamFoundingYear.getText().toString().isEmpty()){

                    if(Integer.parseInt(teamSportCode.getText().toString()) > sports.size()-1){
                        Toast.makeText(view.getContext(),"Sport doesn't exist",Toast.LENGTH_SHORT).show();
                    }else {

                        if(tNew || teams.size() == 0) {
                            teamDAO.insert(new Team(0, teamName.getText().toString(), teamStadium.getText().toString(), teamCity.getText().toString(), teamCountry.getText().toString(), Integer.parseInt(teamSportCode.getText().toString()), Integer.parseInt(teamFoundingYear.getText().toString())));
                            Toast.makeText(view.getContext(), "Added Team", Toast.LENGTH_SHORT).show();
                            tNew = false;
                        }else{
                            teamDAO.update(new Team(teams.get(teamIndex).tid, teamName.getText().toString(), teamStadium.getText().toString(), teamCity.getText().toString(), teamCountry.getText().toString(), Integer.parseInt(teamSportCode.getText().toString()), Integer.parseInt(teamFoundingYear.getText().toString())));
                            Toast.makeText(view.getContext(), "Updated Team", Toast.LENGTH_SHORT).show();
                        }

                        teams = teamDAO.getAll();

                    }

                }

            });

            view.findViewById(R.id.sportsNew).setOnClickListener(v -> {
                sNew = true;
                sportName.setText("");
                sportType.setText("");
                sportGender.setText("");
            });

            view.findViewById(R.id.sportsRemove).setOnClickListener(v -> {

                if(sNew){
                    sNew = false;
                    sportIndex = 0;
                    return;
                }

                if(sports.size() > 0){

                    sportName.setText("");
                    sportType.setText("");
                    sportGender.setText("");

                    sportDAO.delete(sports.get(sportIndex));
                    Toast.makeText(view.getContext(),"Deleted",Toast.LENGTH_SHORT).show();

                    sports = sportDAO.getAll();
                    sportIndex = 0;

                    if(sports.size() > 0) {
                        set(0);
                    }

                }

            });

            view.findViewById(R.id.matchesNew).setOnClickListener(v -> {
                aNew = true;
                athleteName.setText("");
                athleteLastname.setText("");
                athleteCity.setText("");
                athleteCountry.setText("");
                athleteSportCode.setText("");
                athleteBirthYear.setText("");
            });

            view.findViewById(R.id.matchesRemove).setOnClickListener(v -> {

                if(aNew){
                    aNew = false;
                    athleteIndex = 0;
                    return;
                }

                if(athletes.size() > 0){

                    athleteName.setText("");
                    athleteLastname.setText("");
                    athleteCity.setText("");
                    athleteCountry.setText("");
                    athleteSportCode.setText("");
                    athleteBirthYear.setText("");

                    athleteDAO.delete(athletes.get(athleteIndex));
                    Toast.makeText(view.getContext(),"Deleted",Toast.LENGTH_SHORT).show();

                    athletes = athleteDAO.getAll();
                    athleteIndex = 0;

                    if(athletes.size() > 0){
                        set(1);
                    }

                }

            });

            view.findViewById(R.id.teamsNew).setOnClickListener(v -> {
                tNew = true;
                teamName.setText("");
                teamStadium.setText("");
                teamCity.setText("");
                teamCountry.setText("");
                teamSportCode.setText("");
                teamFoundingYear.setText("");
            });

            view.findViewById(R.id.teamsRemove).setOnClickListener(v -> {

                if(tNew){
                    tNew = false;
                    teamIndex = 0;
                    return;
                }

                if(teams.size() > 0){

                    teamName.setText("");
                    teamStadium.setText("");
                    teamCity.setText("");
                    teamCountry.setText("");
                    teamSportCode.setText("");
                    teamFoundingYear.setText("");

                    teamDAO.delete(teams.get(teamIndex));
                    Toast.makeText(view.getContext(),"Deleted",Toast.LENGTH_SHORT).show();

                    teams = teamDAO.getAll();
                    teamIndex = 0;

                    if(teams.size() > 0) {
                        set(2);
                    }

                }

            });

            getView().findViewById(R.id.sportsNext).setOnClickListener(v -> {

                if(sNew){
                    sNew = false;
                    sportIndex = 0;
                    return;
                }

                if (sports.size() > 0) {

                    if (sportIndex == sports.size() - 1) {
                        sportIndex = 0;
                    } else {
                        sportIndex++;
                    }

                    set(0);

                }

            });

            view.findViewById(R.id.sportsPrev).setOnClickListener(v -> {

                if(sNew){
                    sNew = false;
                    sportIndex = 0;
                    return;
                }

                if (sports.size() > 0) {

                    if (sportIndex == 0) {
                        sportIndex = sports.size() - 1;
                    } else {
                        sportIndex--;
                    }

                    set(0);

                }

            });

            view.findViewById(R.id.athletesNext).setOnClickListener(v -> {

                if(aNew){
                    aNew = false;
                    athleteIndex = 0;
                    return;
                }

                if (athletes.size() > 0) {

                    if (athleteIndex == athletes.size() - 1) {
                        athleteIndex = 0;
                    } else {
                        athleteIndex++;
                    }

                    set(1);

                }

            });

            view.findViewById(R.id.athletesPrev).setOnClickListener(v -> {

                if(aNew){
                    aNew = false;
                    athleteIndex = 0;
                    return;
                }

                if (athletes.size() > 0) {

                    if (athleteIndex == 0) {
                        athleteIndex = athletes.size() - 1;
                    } else {
                        athleteIndex--;
                    }

                    set(1);

                }

            });

            view.findViewById(R.id.teamsNext).setOnClickListener(v -> {

                if(tNew){
                    tNew = false;
                    teamIndex = 0;
                    return;
                }

                if (teams.size() > 0) {

                    if (teamIndex == teams.size() - 1) {
                        teamIndex = 0;
                    } else {
                        teamIndex++;
                    }

                    set(2);

                }

            });

            view.findViewById(R.id.teamsPrev).setOnClickListener(v -> {

                if(tNew){
                    tNew = false;
                    teamIndex = 0;
                    return;
                }

                if (teams.size() > 0) {

                    if (teamIndex == 0) {
                        teamIndex = teams.size() - 1;
                    } else {
                        teamIndex--;
                    }

                    set(2);

                }

            });

        }

    }

    void set(int what){

        if(what == 0){
            sportName.setText(sports.get(sportIndex).name);
            sportType.setText(sports.get(sportIndex).type);
            sportGender.setText(sports.get(sportIndex).gender);
        }else if(what == 1){
            athleteName.setText(athletes.get(athleteIndex).name);
            athleteLastname.setText(athletes.get(athleteIndex).lastname);
            athleteCity.setText(athletes.get(athleteIndex).city);
            athleteCountry.setText(athletes.get(athleteIndex).country);
            athleteSportCode.setText(String.valueOf(athletes.get(athleteIndex).sportCode));
            athleteBirthYear.setText(String.valueOf(athletes.get(athleteIndex).birthYear));
        }else if(what == 2){
            teamName.setText(teams.get(teamIndex).name);
            teamStadium.setText(teams.get(teamIndex).stadium);
            teamCity.setText(teams.get(teamIndex).city);
            teamCountry.setText(teams.get(teamIndex).country);
            teamSportCode.setText(String.valueOf(teams.get(teamIndex).sportCode));
            teamFoundingYear.setText(String.valueOf(teams.get(teamIndex).foundingYear));
        }

    }

}

