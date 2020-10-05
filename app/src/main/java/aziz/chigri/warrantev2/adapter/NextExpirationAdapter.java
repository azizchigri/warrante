package aziz.chigri.warrantev2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import aziz.chigri.warrantev2.R;
import aziz.chigri.warrantev2.model.Bill;
import aziz.chigri.warrantev2.view.NextExpirationViewHolder;

public class NextExpirationAdapter extends RecyclerView.Adapter<NextExpirationViewHolder> {

    private List<Bill> billList;

    public NextExpirationAdapter(List<Bill> billList) {
        this.billList = billList;
    }

    @NonNull
    @Override
    public NextExpirationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_next_expiration_item, parent, false);

        return new NextExpirationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NextExpirationViewHolder holder, int position) {
        holder.updateWithNewBill(this.billList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.billList.size();
    }

    public void update(List<Bill> billList) {
        this.billList = billList;
        this.notifyDataSetChanged();
    }
}
