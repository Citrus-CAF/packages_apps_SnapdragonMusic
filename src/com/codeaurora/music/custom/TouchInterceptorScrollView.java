/*
 * Copyright (c) 2015, The Linux Foundation. All rights reserved.

 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials provided
 *       with the distribution.
 *     * Neither the name of The Linux Foundation nor the names of its
 *       contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.

 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
 * IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.codeaurora.music.custom;

import com.android.music.R;
import com.android.music.TouchInterceptor;
import com.android.music.R.dimen;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;

public class TouchInterceptorScrollView extends TouchInterceptor {

    private final String TAG = "TouchInterceptorScrollView";
    private Context mContext;
    private WindowManager mWM;

    public TouchInterceptorScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = 0;
        try {
            count = getAdapter().getCount();
        } catch (NullPointerException e) {
            Log.e(TAG, "exception caught");
        }
        setMeasuredDimension(getDisplayWidth(), (int) (count * getResources()
                .getDimension(R.dimen.list_item_size)));
    }
    private int getDisplayWidth() {
        int width = SCREEN_WIDTH;
        if (mWM != null) {
            width = mWM.getDefaultDisplay().getWidth();
        } else {
            mWM = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            if (mWM != null) {
                width = mWM.getDefaultDisplay().getWidth();
            }
        }
        return width;
    }

}
