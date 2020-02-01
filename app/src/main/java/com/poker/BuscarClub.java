package com.poker;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class BuscarClub extends DialogFragment {
    private EditText etNombre;
    private TextView mActionOK, mActionCancel, tvBuscarClubFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_buscar_club, container, false);

        mActionOK = (TextView) v.findViewById(R.id.action_ok_buscar);
        mActionCancel = (TextView) v.findViewById(R.id.action_cancel_buscar);
        etNombre = (EditText) v.findViewById(R.id.etNombreBuscarDialog);

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
                ClubesFragment fragment = (ClubesFragment) getActivity().getFragmentManager().findFragmentById(R.layout.fragment_clubes);
                tvBuscarClubFragment = fragment.tvBuscarClub;
                tvBuscarClubFragment.setText(input);
                getDialog().dismiss();
            }
        });

        return v;
    }
}
