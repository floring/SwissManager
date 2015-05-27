package com.arles.swissmanager.utils;

import android.graphics.*;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.ShapeDrawable;

/**
 * CircleItemDrawable draws circle icon with white letter inside it.
 * CircleItemDrawable used in RecyclerView.ViewHolder to provide a nice view to layout item.
 *
 * Created by Admin on 26.05.2015.
 */
public class CircleIconDrawable extends ShapeDrawable {

    private static final float SHADE_FACTOR = 0.9f;

    private final Paint mTextPaint;
    private final Paint mBorderPaint;
    private final String mText;
    private final int mColor;
    private final RectShape mShape;
    private final int mHeight;
    private final int mWidth;
    private final int mFontSize;
    private final float mRadius;
    private final int mBorderThickness;

    private CircleIconDrawable(Builder builder) {
        super(builder.mShape);

        // mShape properties
        mShape = builder.mShape;
        mHeight = builder.mHeight;
        mWidth = builder.mWidth;
        mRadius = builder.radius;

        // mText and mColor
        mText = builder.toUpperCase ? builder.mText.toUpperCase() : builder.mText;
        mColor = builder.mColor;

        // mText paint settings
        mFontSize = builder.mFontSize;
        mTextPaint = new Paint();
        mTextPaint.setColor(builder.mTextColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setFakeBoldText(builder.isBold);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTypeface(builder.mFont);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setStrokeWidth(builder.mBorderThickness);

        // border paint settings
        mBorderThickness = builder.mBorderThickness;
        mBorderPaint = new Paint();
        mBorderPaint.setColor(getDarkerShade(mColor));
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(mBorderThickness);

        // drawable paint mColor
        Paint paint = getPaint();
        paint.setColor(mColor);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    private int getDarkerShade(int color) {
        return Color.rgb((int)(SHADE_FACTOR * Color.red(color)),
                (int)(SHADE_FACTOR * Color.green(color)),
                (int)(SHADE_FACTOR * Color.blue(color)));
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect r = getBounds();

        // draw border
        if (mBorderThickness > 0) {
            drawBorder(canvas);
        }

        int count = canvas.save();
        canvas.translate(r.left, r.top);

        // draw mText
        int width = mWidth < 0 ? r.width() : mWidth;
        int height = mHeight < 0 ? r.height() : mHeight;
        int fontSize = mFontSize < 0 ? (Math.min(width, height) / 2) : mFontSize;
        mTextPaint.setTextSize(fontSize);
        canvas.drawText(mText, width / 2, height / 2 - ((mTextPaint.descent() + mTextPaint.ascent()) / 2), mTextPaint);

        canvas.restoreToCount(count);

    }

    private void drawBorder(Canvas canvas) {
        RectF rect = new RectF(getBounds());
        rect.inset(mBorderThickness /2, mBorderThickness /2);

        if (mShape instanceof OvalShape) {
            canvas.drawOval(rect, mBorderPaint);
        }
        else if (mShape instanceof RoundRectShape) {
            canvas.drawRoundRect(rect, mRadius, mRadius, mBorderPaint);
        }
        else {
            canvas.drawRect(rect, mBorderPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        mTextPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mTextPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return mWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return mHeight;
    }

    public static IShapeBuilder builder() {
        return new Builder();
    }

    public static class Builder implements IConfigurationBuilder, IShapeBuilder, IBuilder {

        private String mText;
        private int mColor;
        private int mBorderThickness;
        private int mWidth;
        private int mHeight;
        private Typeface mFont;
        private RectShape mShape;
        public int mTextColor;
        private int mFontSize;
        private boolean isBold;
        private boolean toUpperCase;
        private static final String FONT = "sans-serif-light";

        public float radius;

        private Builder() {
            mText = "";
            mColor = Color.GRAY;
            mTextColor = Color.WHITE;
            mBorderThickness = 0;
            mWidth = -1;
            mHeight = -1;
            mShape = new RectShape();
            mFont = Typeface.create(FONT, Typeface.NORMAL);
            mFontSize = -1;
            isBold = false;
            toUpperCase = false;
        }

        public IConfigurationBuilder width(int width) {
            mWidth = width;
            return this;
        }

        public IConfigurationBuilder height(int height) {
            mHeight = height;
            return this;
        }

        public IConfigurationBuilder textColor(int color) {
            mTextColor = color;
            return this;
        }

        public IConfigurationBuilder withBorder(int thickness) {
            mBorderThickness = thickness;
            return this;
        }

        public IConfigurationBuilder useFont(Typeface font) {
            mFont = font;
            return this;
        }

        public IConfigurationBuilder fontSize(int size) {
            mFontSize = size;
            return this;
        }

        public IConfigurationBuilder bold() {
            isBold = true;
            return this;
        }

        public IConfigurationBuilder toUpperCase() {
            toUpperCase = true;
            return this;
        }

        @Override
        public IConfigurationBuilder startConfiguration() {
            return this;
        }

        @Override
        public IShapeBuilder endConfiguration() {
            return this;
        }

        @Override
        public IBuilder rect() {
            mShape = new RectShape();
            return this;
        }

        @Override
        public IBuilder round() {
            mShape = new OvalShape();
            return this;
        }

        @Override
        public IBuilder roundRect(int radius) {
            this.radius = radius;
            float[] radii = {radius, radius, radius, radius, radius, radius, radius, radius};
            mShape = new RoundRectShape(radii, null, null);
            return this;
        }

        @Override
        public CircleIconDrawable buildRect(String text, int color) {
            rect();
            return build(text, color);
        }

        @Override
        public CircleIconDrawable buildRoundRect(String text, int color, int radius) {
            roundRect(radius);
            return build(text, color);
        }

        @Override
        public CircleIconDrawable buildRound(String text, int color) {
            round();
            return build(text, color);
        }

        @Override
        public CircleIconDrawable build(String text, int color) {
            mColor = color;
            mText = text;
            return new CircleIconDrawable(this);
        }
    }

    public interface IConfigurationBuilder {
        public IConfigurationBuilder width(int width);

        public IConfigurationBuilder height(int height);

        public IConfigurationBuilder textColor(int color);

        public IConfigurationBuilder withBorder(int thickness);

        public IConfigurationBuilder useFont(Typeface font);

        public IConfigurationBuilder fontSize(int size);

        public IConfigurationBuilder bold();

        public IConfigurationBuilder toUpperCase();

        public IShapeBuilder endConfiguration();
    }

    public static interface IBuilder {

        public CircleIconDrawable build(String text, int color);
    }

    public static interface IShapeBuilder {

        public IConfigurationBuilder startConfiguration();

        public IBuilder rect();

        public IBuilder round();

        public IBuilder roundRect(int radius);

        public CircleIconDrawable buildRect(String text, int color);

        public CircleIconDrawable buildRoundRect(String text, int color, int radius);

        public CircleIconDrawable buildRound(String text, int color);
    }

}
