package com.polinema.meme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static com.polinema.meme.Resep.resepmeme;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResepDetailFragment extends Fragment {

    private long resepId;

    public ResepDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resep_detail, container, false);
    }

    public void setResep(long id){
        this.resepId = id;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            TextView tittle = (TextView) view.findViewById(R.id.textJudul);
            Resep resep = Resep.resepmeme[(int) resepId];
            tittle.setText(resep.getNama_meme());

            TextView detail = (TextView) view.findViewById(R.id.textDetail);
            detail.setText(resep.getDetail_meme());

            TextView rating = (TextView) view.findViewById(R.id.textRating);
            rating.setText(resep.getRating_meme());

            ImageView gambar = (ImageView) view.findViewById(R.id.imgmeme);
            gambar.setImageResource(resep.getGambar_meme());
        }
    }

}
