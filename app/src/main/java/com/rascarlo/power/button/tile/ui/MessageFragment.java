package com.rascarlo.power.button.tile.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.rascarlo.power.button.tile.R;
import com.rascarlo.power.button.tile.utils.PowerButtonTileConstants;

public class MessageFragment extends Fragment {
    private static final String ARG_PARAMETER = "arg_parameter";

    private String argParameter;

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance(String param1) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAMETER, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            argParameter = getArguments().getString(ARG_PARAMETER);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);
        ImageView imageView = rootView.findViewById(R.id.fragment_message_image_view);
        imageView.setColorFilter(ContextCompat.getColor(container.getContext(), R.color.colorAccent));
        TextView textViewTitle = rootView.findViewById(R.id.fragment_message_title_text_view);
        TextView textViewSummary = rootView.findViewById(R.id.fragment_message_summary_text_view);
        if (argParameter != null && !TextUtils.isEmpty(argParameter)) {
            if (TextUtils.equals(PowerButtonTileConstants.MESSAGE_ACCESSIBILITY_SERVICE_DISABLED, argParameter)) {
                imageView.setImageDrawable(ContextCompat.getDrawable(container.getContext(), R.drawable.ic_accessibility_white_24dp));
                textViewTitle.setText(getString(R.string.accessibility_service_label));
                textViewSummary.setText(getString(R.string.message_accessibility_service_disabled));
            } else if (TextUtils.equals(PowerButtonTileConstants.MESSAGE_SOMETHING_WENT_WRONG, argParameter)) {
                imageView.setImageDrawable(ContextCompat.getDrawable(container.getContext(), R.drawable.ic_error_outline_white_24dp));
                textViewTitle.setText(getString(R.string.message_something_went_wrong));
                textViewSummary.setText(getString(R.string.message_please_try_again));
            }
        }
        return rootView;
    }
}
