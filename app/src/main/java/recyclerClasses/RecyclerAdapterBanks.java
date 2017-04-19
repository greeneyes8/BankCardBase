package recyclerClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mohse78.bankcardbase.Activity_Banks;
import com.mohse78.bankcardbase.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by ab.mohammadi on 2/5/2017.
 */
public class RecyclerAdapterBanks extends RecyclerView.Adapter<RecyclerAdapterBanks.VH> {

    private Context context;
    private List<TextAndLogo> data;
    private LayoutInflater inflater;
    private int positionSelected;
    public static final String LAST_BANK_SELECTED_LOGO = "LAST_BANK_SELECTED_LOGO";
    public static final String LAST_BANK_SELECTED_NAME = "LAST_BANK_SELECTED_NAME";
    public static final String LAST_BANK_SELECTED_ID = "LAST_BANK_SELECTED_ID";
    public static final String NEW_BANK_PAGE= "NEW_BANK_PAGE";


    public RecyclerAdapterBanks(Context context, List<TextAndLogo> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_view_text_and_logo , parent , false);
        VH holder = new VH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        TextAndLogo tl = data.get(position);
        holder.setData(tl,position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class VH extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvBankName ;
        ImageView ivBankLogo;

        public VH(View itemView) {
            super(itemView);
            tvBankName = (TextView) itemView.findViewById(R.id.text_card_textandlogo_text);
            ivBankLogo = (ImageView) itemView.findViewById(R.id.image_card_textandlogo_logo);
            itemView.setOnClickListener(this);
        }

        public void setData(TextAndLogo tl, int position) {
            tvBankName.setText(tl.getText());
            ivBankLogo.setImageResource(tl.getImageLogoId());
        }

        @Override
        public void onClick(View v) {

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if(getAdapterPosition() == data.size()-1){
                editor.putBoolean(NEW_BANK_PAGE , true);
            }else {
                editor.putLong(LAST_BANK_SELECTED_ID , getAdapterPosition()+1);
                editor.putString(LAST_BANK_SELECTED_NAME , data.get(getAdapterPosition()).getText());
                editor.putInt(LAST_BANK_SELECTED_LOGO , data.get(getAdapterPosition()).getImageLogoId());
            }
            editor.commit();
            ((Activity_Banks)context).finish();
        }
    }
}
