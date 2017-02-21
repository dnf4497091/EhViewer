/*
 * Copyright 2017 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.ehviewer.activity;

/*
 * Created by Hippo on 2/10/2017.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

public abstract class ControllerActivity extends AppCompatActivity {

  private static final String LOG_TAG = ControllerActivity.class.getSimpleName();

  @Nullable
  private Router router;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onSetContentView();
    router = Conductor.attachRouter(this, getControllerContainer(), savedInstanceState);
    if (!router.hasRootController()) {
      // It's root controller, doesn't need ChangeHandler
      RouterTransaction transaction = RouterTransaction.with(getRootController());
      router.setRoot(transaction);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    router = null;
  }

  @Override
  public void onBackPressed() {
    if (router == null || !router.handleBack()) {
      super.onBackPressed();
    }
  }

  /**
   * Push a {@code Controller} to this {@code ControllerActivity}.
   */
  public void pushController(@NonNull RouterTransaction transaction) {
    if (router != null) {
      router.pushController(transaction);
    } else {
      Log.e(LOG_TAG, "router == null, can't push controller");
    }
  }

  /**
   * Replaces top controller of this {@code ControllerActivity}.
   */
  public void replaceTopController(@NonNull RouterTransaction transaction) {
    if (router != null) {
      router.replaceTopController(transaction);
    } else {
      Log.e(LOG_TAG, "router == null, can't replace top controller");
    }
  }

  /**
   * Sets content view for this {@code Activity}.
   * <p>
   * Please call {@link #setContentView(int)}, {@link #setContentView(View)} or
   * {@link #setContentView(View, ViewGroup.LayoutParams)} here.
   * <p>
   * Called in {@link #onCreate(Bundle)}.
   */
  protected abstract void onSetContentView();

  /**
   * Returns a container for controllers.
   */
  @NonNull
  protected abstract ViewGroup getControllerContainer();

  /**
   * Returns root controller for this {@code ControllerActivity}.
   */
  @NonNull
  protected abstract Controller getRootController();
}
