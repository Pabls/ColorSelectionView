package ru.ar4i.colorselectionview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class ColorSelectionView extends ScrollView {

    private static final float ONE_PERCENT = 2.55F;
    private static final int DEFAULT_RED_COLOR_VALUE = 255;
    private static final int DEFAULT_GREEN_COLOR_VALUE = 160;
    private static final int DEFAULT_BLUE_COLOR_VALUE = 0;

    private TextView tvTitle;
    private View vSelectedColor;
    private SeekBar sbRed;
    private SeekBar sbGreen;
    private SeekBar sbBlue;
    private LinearLayout llColors;
    private View vRed;
    private View vMullbery;
    private View vViolet;
    private View vGrape;
    private View vDarkBlue;
    private View vLightBlue;
    private View vAzure;
    private View vSky;
    private View vAquamarine;
    private View vEmerald;
    private LinearLayout llButtons;
    private Button btnCancel;
    private Button btnOk;

    public ColorSelectionView(Context context) {
        super(context);
        initView(context);
    }

    public ColorSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        getAttrs(context, attrs);
    }

    public ColorSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        getAttrs(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ColorSelectionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
        getAttrs(context, attrs);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.ColorSelectionView, 0, 0);
        if (array != null) {
            showTitle(array.getBoolean(R.styleable.ColorSelectionView_csv_show_title, true));
            showSeekBars(array.getBoolean(R.styleable.ColorSelectionView_csv_show_seek_bars, true));
            showColorPalette(array.getBoolean(R.styleable.ColorSelectionView_csv_show_color_palette, true));
            showButtons(array.getBoolean(R.styleable.ColorSelectionView_csv_show_buttons, true));
            setText(btnCancel, array.getString(R.styleable.ColorSelectionView_csv_cancel_button_text));
            setText(btnOk, array.getString(R.styleable.ColorSelectionView_csv_ok_button_text));
            setText(tvTitle, array.getString(R.styleable.ColorSelectionView_csv_title));
        }
    }

    public void showTitle(boolean show) {
        showView(tvTitle, show);
    }

    public void setTitle(String title) {
        setText(tvTitle, title);
    }

    public void showButtons(boolean show) {
        showView(llButtons, show);
    }

    public void setCancelBtnText(String text) {
        setText(btnCancel, text);
    }

    public void setOkBtnText(String text) {
        setText(btnOk, text);
    }

    public void showSeekBars(boolean show) {
        int showValue = show ? VISIBLE : GONE;
        showView(sbRed, show);
        showView(sbGreen, show);
        showView(sbBlue, show);
    }

    public void showColorPalette(boolean show) {
        showView(llColors, show);
    }

    private void setText(TextView textView, String text) {
        if (textView != null && text != null && !text.isEmpty())
            textView.setText(text);
    }

    private void showView(View view, boolean show) {
        if (view != null)
            view.setVisibility(getVisibilityValue(show));
    }

    private int getVisibilityValue(boolean show) {
        return show ? VISIBLE : GONE;
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.color_selection_view, this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        findViews();
    }

    private void findViews() {
        tvTitle = findViewById(R.id.tv_title);
        vSelectedColor = findViewById(R.id.v_selected_color);
        sbRed = findViewById(R.id.sb_red);
        sbGreen = findViewById(R.id.sb_green);
        sbBlue = findViewById(R.id.sb_blue);
        llColors = findViewById(R.id.ll_colors);
        vRed = findViewById(R.id.v_red);
        vMullbery = findViewById(R.id.v_mulberry);
        vViolet = findViewById(R.id.v_violet);
        vGrape = findViewById(R.id.v_grape);
        vDarkBlue = findViewById(R.id.v_dark_blue);
        vLightBlue = findViewById(R.id.v_light_blue);
        vAzure = findViewById(R.id.v_azure);
        vSky = findViewById(R.id.v_sky);
        vAquamarine = findViewById(R.id.v_aquamarine);
        vEmerald = findViewById(R.id.v_emerald);
        llButtons = findViewById(R.id.ll_buttons);
        btnCancel = findViewById(R.id.btn_cancel);
        btnOk = findViewById(R.id.btn_ok);
        setDefaultColor();
        setListeners();
    }

    private void setListeners() {
        sbRed.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                changeSelectedColorView(getColor(i, sbGreen.getProgress(), sbBlue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        sbGreen.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                changeSelectedColorView(getColor(sbRed.getProgress(), i, sbBlue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        sbBlue.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                changeSelectedColorView(getColor(sbRed.getProgress(), sbGreen.getProgress(), i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        vRed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorsValue(view);
            }
        });

        vMullbery.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorsValue(view);
            }
        });
        
        vViolet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorsValue(view);
            }
        });

        vGrape.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorsValue(view);
            }
        });

        vDarkBlue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorsValue(view);
            }
        });

        vLightBlue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorsValue(view);
            }
        });

        vAzure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorsValue(view);
            }
        });

        vSky.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorsValue(view);
            }
        });

        vAquamarine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorsValue(view);
            }
        });

        vEmerald.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorsValue(view);
            }
        });
    }

    private void changeColorsValue(View view) {
        int color = getBackgroundColor(view);
        String hexColor = String.format("%06X", (0xFFFFFF & color));
        if (hexColor != null && hexColor.length() == 6) {
            sbRed.setProgress(convertColorToSeekBarValue(Integer.parseInt(hexColor.substring(0, 2), 16)));
            sbGreen.setProgress(convertColorToSeekBarValue(Integer.parseInt(hexColor.substring(2, 4), 16)));
            sbBlue.setProgress(convertColorToSeekBarValue(Integer.parseInt(hexColor.substring(4), 16)));
        }
        changeSelectedColorView(color);
    }

    private void setDefaultColor() {
        sbRed.setProgress(convertColorToSeekBarValue(DEFAULT_RED_COLOR_VALUE));
        sbGreen.setProgress(convertColorToSeekBarValue(DEFAULT_GREEN_COLOR_VALUE));
        sbBlue.setProgress(convertColorToSeekBarValue(DEFAULT_BLUE_COLOR_VALUE));
        changeSelectedColorView(
                createColor(DEFAULT_RED_COLOR_VALUE, DEFAULT_GREEN_COLOR_VALUE, DEFAULT_BLUE_COLOR_VALUE)
        );
    }

    private int getColor(int redSeekBarValue, int greenSeekBarValue, int blueSeekBarValue) {
        int red = convertSeekBarValueToColor(redSeekBarValue);
        int green = convertSeekBarValueToColor(greenSeekBarValue);
        int blue = convertSeekBarValueToColor(blueSeekBarValue);
        return createColor(red, green, blue);
    }

    private int createColor(int red, int green, int blue) {
        return Color.rgb(red, green, blue);
    }

    private int convertSeekBarValueToColor(int value) {
        double result = ONE_PERCENT * value;
        return (int) result;
    }

    private int convertColorToSeekBarValue(int color) {
        double result = color / ONE_PERCENT;
        return (int) result;
    }

    private void changeSelectedColorView(int color) {
        vSelectedColor.setBackgroundColor(color);
    }

    private int getBackgroundColor(View view) {
        int color = Color.TRANSPARENT;
        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        return color;
    }
}
