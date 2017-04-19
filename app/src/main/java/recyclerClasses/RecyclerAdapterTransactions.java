package recyclerClasses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mohse78.bankcardbase.AccountTransaction;
import com.mohse78.bankcardbase.R;
import com.mohse78.bankcardbase.TransactionType;

import java.text.SimpleDateFormat;
import java.util.List;

import DB.DBFactory;

/**
 * Created by ab.mohammadi on 2/8/2017.
 */
public class RecyclerAdapterTransactions extends RecyclerView.Adapter<RecyclerAdapterTransactions.VH> {

    List<AccountTransaction> accountTransactions;
    LayoutInflater inflater;
    Context context;

    public RecyclerAdapterTransactions(Context context , List<AccountTransaction> accountTransactions) {
        this.accountTransactions = accountTransactions;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_view_transaction , parent , false);
        VH holder = new VH(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return accountTransactions.size();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        AccountTransaction transaction = accountTransactions.get(position);
        holder.setData(transaction , position);
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tvType;
        TextView tvAmount;
        TextView tvBalance;
        TextView tvDate;
        TextView tvDescription;
        ImageView ivTypeImage;
        LinearLayout linearLayout;

        public VH(View itemView) {
            super(itemView);
             tvType = (TextView) itemView.findViewById(R.id.text_transaction_view_typeName);
             tvAmount = (TextView) itemView.findViewById(R.id.text_transaction_view_amount);
             tvBalance= (TextView) itemView.findViewById(R.id.text_transaction_view_balance);
             tvDate= (TextView) itemView.findViewById(R.id.text_transaction_view_date);
             tvDescription= (TextView) itemView.findViewById(R.id.text_transaction_view_description);
             ivTypeImage = (ImageView) itemView.findViewById(R.id.image_transaction_view_typeImage);
             linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_transaction_view_back);
        }

        public void setData(AccountTransaction transaction, int position) {
            if(transaction.getDebit() > 0) {
                tvType.setText(R.string.debit2);
                tvAmount.setText(transaction.getDebit()+"");
                ivTypeImage.setImageResource(R.drawable.debit_red);
                linearLayout.setBackgroundResource(R.drawable.back_trans_gray);
            }
            if(transaction.getCredit() > 0) {
                tvType.setText(R.string.credit2);
                tvAmount.setText(transaction.getCredit()+"");
                ivTypeImage.setImageResource(R.drawable.credit);
                linearLayout.setBackgroundResource(R.drawable.shadow);
            }
            tvBalance.setText(transaction.getCurrentBalance()+"");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            tvDate.setText(simpleDateFormat.format(transaction.getCreationDate()));
            tvDescription.setText(transaction.getDescription());
            TransactionType tt = new DBFactory(context).getTransactionType(transaction.getTransactionType());
            int imageId = tt.getImageLogoId();
            ivTypeImage.setImageResource(imageId);
        }
    }
}
