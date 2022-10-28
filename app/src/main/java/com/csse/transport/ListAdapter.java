package com.csse.transport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {

    private Activity pContext;
    List<CardPayments> cardPaymentsList;

    public ListAdapter(Activity pContext, List<CardPayments> cardPaymentsList){
        super(pContext,R.layout.list_cardpayments, cardPaymentsList);
        this.pContext = pContext;
        this.cardPaymentsList = cardPaymentsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = pContext.getLayoutInflater();
        View listPaymentView = inflater.inflate(R.layout.list_cardpayments, null, true);

        TextView tvAddress = listPaymentView.findViewById(R.id.tvAddress);
        TextView tvNumber = listPaymentView.findViewById(R.id.tvNumber);
        TextView tvCard = listPaymentView.findViewById(R.id.tvCard);

        CardPayments cardPayments = cardPaymentsList.get(position);

        tvAddress.setText(cardPayments.getAddress());
        tvNumber.setText(cardPayments.getNumber());
        tvCard.setText(cardPayments.getCard());

        return listPaymentView;
    }
}
