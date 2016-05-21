/*
 * Copyright (C) 2014 pengjianbo(pengjianbosoft@gmail.com), Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.luosifan.photopicker.demo.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.AbsListView;

import com.luosifan.photopicker.utils.ImageLoader;
import com.luosifan.photopicker.view.GFImageView;
import com.squareup.picasso.Picasso;

import java.io.File;


/**
 * Desction:
 * Author:pengjianbo
 * Date:15/12/1 下午10:26
 */
public class PicassoImageLoader implements ImageLoader {

    private Bitmap.Config mConfig;

    public PicassoImageLoader() {
        this(Bitmap.Config.RGB_565);
    }

    public PicassoImageLoader(Bitmap.Config config) {
        this.mConfig = config;
    }


    @Override
    public void displayImage(Context mCxt, String path, GFImageView imageView, String tag, int placeholderResId, int errorResId, int width, int height) {

        Picasso.with(mCxt)
                .load(new File(path))
                .placeholder(placeholderResId)
                .error(errorResId)
                .config(mConfig)
                .resize(width, height)
                .centerCrop()
                .tag(tag)
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState, String tag) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
            Picasso.with(view.getContext()).pauseTag(tag);
        } else {
            Picasso.with(view.getContext()).resumeTag(tag);
        }
    }
}
