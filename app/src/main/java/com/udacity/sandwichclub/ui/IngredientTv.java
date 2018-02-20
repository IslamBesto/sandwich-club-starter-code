package com.udacity.sandwichclub.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.udacity.sandwichclub.R;

/**
 * Created by saidi on 17/02/2018.
 * Custom UI to display ingredients
 */

public class IngredientTv extends FrameLayout {
    private TextView mIngredientTv;

    public IngredientTv(@NonNull Context context) {
        super(context);
        init(context);
    }

    public IngredientTv(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IngredientTv(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public IngredientTv(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context) {
        LayoutInflater.from(context).inflate(getLayoutId(), this);
        mIngredientTv = findViewById(R.id.ingredients_tv);
    }

    public int getLayoutId() {
        return R.layout.ingredient_tv;
    }

    /**
     * Set Text to ingredient
     * @param ingredientText Text to set
     */
    public void setIngredientTv(String ingredientText) {
        mIngredientTv.setText(ingredientText);
    }
}
