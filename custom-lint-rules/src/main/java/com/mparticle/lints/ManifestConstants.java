/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mparticle.lints;

/**
 * Manifest Constant definition class.
 * Amends constants not found in {@link com.android.SdkConstants}.
 */
public final class ManifestConstants {
    public static final String CATEGORY_NAME_LAUNCHER = "android.intent.category.LAUNCHER";
    public static final String ACTION_NAME_MAIN = "android.intent.action.MAIN";
    public static final String ACTION_INSTALL_REFERRER = "com.android.vending.INSTALL_REFERRER";
    public static final String MP_RECEIVER_NAME = "com.mparticle.ReferrerReceiver";
}
