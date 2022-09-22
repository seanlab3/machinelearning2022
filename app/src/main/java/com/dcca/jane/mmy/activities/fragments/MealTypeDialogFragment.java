package com.dcca.jane.mmy.activities.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
//import android.support.design.widget.BottomSheetDialogFragment;

//sean_0831
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dcca.jane.mmy.R;
import com.dcca.jane.mmy.activities.DailyViewActivity;
import com.dcca.jane.mmy.activities.MealBuilderActivity;
import com.dcca.jane.mmy.data.AppDatabase;

import static com.dcca.jane.mmy.activities.DailyViewActivity.checkB;
import static com.dcca.jane.mmy.activities.DailyViewActivity.checkD;
import static com.dcca.jane.mmy.activities.DailyViewActivity.checkL;
import static com.dcca.jane.mmy.activities.DailyViewActivity.checkS;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     MealTypeDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement {@link MealTypeDialogFragment.Listener}.</p>
 */
public class MealTypeDialogFragment extends BottomSheetDialogFragment {

    // TODO: change extra string to reflect package when app name changed
    public static final String EXTRA_MEAL_ID = "com.dcca.jane.mmy.MEAL_ID";
    public static final String EXTRA_MEAL_TYPE = "com.dcca.jane.mmy.MEAL_TYPE";
    public static final String EXTRA_MEAL_YEAR = "com.dcca.jane.mmy.MEAL_YEAR";
    public static final String EXTRA_MEAL_MONTH = "com.dcca.jane.mmy.MEAL_MONTH";
    public static final String EXTRA_MEAL_DAY = "com.dcca.jane.mmy.MEAL_DAY";
    public static final String EXTRA_OVERWRITE_CASE = "com.dcca.jane.mmy.OVERWRITE_CASE";
    private static final String ARG_ITEM_COUNT = "item_count";
    private Listener mListener;

    int overWrite;

//    Boolean mB;
//    Boolean mL;
//    Boolean mD;
//    Boolean mS;

    private AppDatabase mDb;

    //Edited code
    public static MealTypeDialogFragment newInstance() {
        int itemCount = 4;
        final MealTypeDialogFragment fragment = new MealTypeDialogFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_type_dialog, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_meal_type_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(getArguments().getInt(ARG_ITEM_COUNT)));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        final Fragment parent = getParentFragment();
        if (parent != null) {
            mListener = (Listener) parent;
        } else {
            mListener = (Listener) context;
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    public interface Listener {
        void onItemClicked(int position);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView text;
        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.fragment_meal_type_dialog_item, parent, false));
            text = itemView.findViewById(R.id.text);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClicked(getAdapterPosition());
                        int mealType = getAdapterPosition() + 1;
                        //Checks if selected mealType already in database
                        boolean duplicateB = mealType == 1 && checkB();
                        boolean duplicateL = mealType == 2 && checkL();
                        boolean duplicateD = mealType == 3 && checkD();
                        boolean duplicateS = mealType == 4 && checkS();
                        if(duplicateB || duplicateL || duplicateD || duplicateS){
//                            throw new NullPointerException("Houston, we have a problem: mealType must be passed to overwrite meal");
                            postNotice(text.getText().toString(), mealType);
                        } else {
                            launchBuilder(mealType,false );
                        }
                    }
                }

                private void launchBuilder(int mealType, boolean overwrite){
                    int year = ((DailyViewActivity)getActivity()).getCurrentYear();
                    int month = ((DailyViewActivity)getActivity()).getCurrentMonth();
                    int day = ((DailyViewActivity)getActivity()).getCurrentDay();


                    Intent intent = new Intent(getActivity(), MealBuilderActivity.class);
                    intent.putExtra(EXTRA_OVERWRITE_CASE, overwrite);
                    intent.putExtra(EXTRA_MEAL_ID, 0);
                    intent.putExtra(EXTRA_MEAL_TYPE, mealType);
                    intent.putExtra(EXTRA_MEAL_YEAR, year);
                    intent.putExtra(EXTRA_MEAL_MONTH, month);
                    intent.putExtra(EXTRA_MEAL_DAY, day);
                    startActivity(intent);
                }

                private void postNotice(String mealTitle, int mealType) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Overwrite Meal")
                            .setMessage("You already have a " + mealTitle + " saved. Would you like to overwrite it?")
                            .setNegativeButton("Cancel", null)
                            .setPositiveButton("Overwrite", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                    launchBuilder(mealType,true);
                                }
                            }).create().show();
                }
            });
        }
    }

    private void createMeal(int mealType){

//        mDb = AppDatabase.getInMemoryDatabase(getContext());
        int year = ((DailyViewActivity)getActivity()).getCurrentYear();
        int month = ((DailyViewActivity)getActivity()).getCurrentMonth();
        int day = ((DailyViewActivity)getActivity()).getCurrentDay();

//        Calendar c = Calendar.getInstance();

//        Date mealTime = makeTimestamp(year, month, day);
//
//        long newMealId = makeBlankMeal(mDb, mealType, mealTime);
//

        Intent intent = new Intent(getActivity(), MealBuilderActivity.class);
        intent.putExtra(EXTRA_MEAL_TYPE, mealType);
//        intent.putExtra(EXTRA_MEAL_TYPE, mealType);
        intent.putExtra(EXTRA_MEAL_YEAR, year);
        intent.putExtra(EXTRA_MEAL_MONTH, month);
        intent.putExtra(EXTRA_MEAL_DAY, day);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final int mItemCount;

        ItemAdapter(int itemCount) {
            mItemCount = itemCount;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        //Edited code
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if(position == 0){
                holder.text.setText(R.string.meal_breakfast);
            }
            if(position == 1){
                holder.text.setText(R.string.meal_lunch);
            }
            if(position == 2){
                holder.text.setText(R.string.meal_dinner);
            }
            if(position == 3){
                holder.text.setText(R.string.meal_snacks);
            }
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }

    }

}
