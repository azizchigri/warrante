package aziz.chigri.warrantev2.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import aziz.chigri.warrantev2.R;
import aziz.chigri.warrantev2.model.Bill;

public class NextExpirationViewHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public NextExpirationViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.fragment_main_item_title);
    }

    public void updateWithNewBill(Bill bill) {
        this.textView.setText(bill.getTitle());
    }
}
