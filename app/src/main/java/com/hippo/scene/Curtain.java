/*
 * Copyright (C) 2015 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.scene;

import android.support.annotation.NonNull;

public abstract class Curtain {

    /**
     * Called when
     *
     * @param enter the scene on the top of the scene stack
     * @param exit the scene behind the enter scene
     */
    public abstract void open(@NonNull Scene enter, @NonNull Scene exit);

    public abstract void close(@NonNull Scene enter, @NonNull Scene exit);

    /**
     * End open or close animation
     */
    public abstract void endAnimation();

    /**
     * Is open or close animation running
     */
    public abstract boolean isInAnimation();

    protected void dispatchOpenFinished(@NonNull Scene enter, @NonNull Scene exit) {

    }

    protected void dispatchCloseFinished(@NonNull Scene enter, @NonNull Scene exit) {
        exit.detachFromeStage();
    }
}
