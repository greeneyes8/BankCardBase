package recyclerClasses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mohse78.bankcardbase.Activity_Transaction;
import com.mohse78.bankcardbase.R;

import java.util.List;

/**
 * Created by ab.mohammadi on 2/5/2017.
 */
public class RecyclerAdapterCards extends RecyclerView.Adapter<RecyclerAdapterCards.VH> {

    public static final String CARDSHOWING_SELECTED = "CARDSHOWING_SELECTED";
    public static final String CASH_MONEY = "CASH_MONEY";
    private Context context;
    private List<CardShowing> cardShowingList;
    private LayoutInflater inflater;

    public RecyclerAdapterCards(Context context, List<CardShowing> cardShowingList) {
        this.context = context;
        this.cardShowingList = cardShowingList;
        inflater =LayoutInflater.from(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_view_card , parent  ,false);
        VH holder = new VH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setData(cardShowingList.get(position) , position);
    }

    @Override
    public int getItemCount() {
        return cardShowingList.size();
    }

    class VH extends RecyclerView.ViewHolder implements View.OnClickListener{

        LinearLayout linearLayout;
        TextView tvCardNumber;
        TextView tvPersonName;
        TextView tvCvv2;
        TextView tvExpDate;
        TextView tvCurrentBalance;



        public VH(View itemView) {
            super(itemView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_card_view_card_back);
            tvCardNumber = (TextView) itemView.findViewById(R.id.text_card_view_card_cardNumber);
            tvPersonName = (TextView) itemView.findViewById(R.id.text_card_view_card_personName);
            tvCvv2 = (TextView) itemView.findViewById(R.id.text_card_view_card_cvv2_content);
            tvExpDate = (TextView) itemView.findViewById(R.id.text_card_view_card_expDate_content);
            tvCurrentBalance = (TextView) itemView.findViewById(R.id.text_card_view_card_balance_content);

            linearLayout.setOnClickListener(this);

        }

        public void setData(CardShowing card , int position){
            linearLayout.setBackgroundResource(card.getBankCardImageId());
            tvCardNumber.setText(card.getCardNumber());
            tvPersonName.setText(card.getPersonName());
            tvCvv2.setText(card.getCvv2());
            tvExpDate.setText(card.getExpDate());
            tvCurrentBalance.setText(card.getCurrentBalance()+"");
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == linearLayout.getId()){
                Bundle bundleCardForEditTrans = new Bundle();
                bundleCardForEditTrans.putSerializable(CARDSHOWING_SELECTED , cardShowingList.get(getAdapterPosition()));
                bundleCardForEditTrans.putBoolean(CASH_MONEY , false);
                Intent intent = new Intent(context , Activity_Transaction.class);
                intent.putExtras(bundleCardForEditTrans);
                context.startActivity(intent);
            }
        }
    }
}
