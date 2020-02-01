package com.poker;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class NuevoClub extends DialogFragment {
    private EditText etNombre;
    private TextView mActionOK, mActionCancel, tvNuevoClub;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_nuevo_club, container, false);

        mActionOK = v.findViewById(R.id.action_ok);
        mActionCancel = v.findViewById(R.id.action_cancel);
        etNombre = v.findViewById(R.id.etNombreClubDialog);

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        mActionOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etNombre.getText().toString();
                if (!input.equals("")) {
                    ClubesFragment fragment = (ClubesFragment) getActivity().getFragmentManager().findFragmentById(R.layout.fragment_clubes);
                    tvNuevoClub = fragment.tvNuevoClub;
                    tvNuevoClub.setText(input);
                }
                getDialog().dismiss();
            }
        });

        return v;
    }
}
