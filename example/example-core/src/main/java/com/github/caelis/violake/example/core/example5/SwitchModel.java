package com.github.caelis.violake.example.core.example5;

import java.util.Objects;

public abstract class SwitchModel {

    private SwitchModel() {
    }

    public static class ImageAndAction extends SwitchModel {
        private final Runnable action;

        public ImageAndAction(Runnable action) {
            this.action = action;
        }

        public Runnable getAction() {
            return action;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ImageAndAction that = (ImageAndAction) o;
            return Objects.equals(action, that.action);
        }

        @Override
        public int hashCode() {
            return Objects.hash(action);
        }
    }

    public static class WebAction extends SwitchModel {

    }

    public static class Progress extends SwitchModel {
        private final String text;

        public Progress(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Progress progress = (Progress) o;
            return Objects.equals(text, progress.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(text);
        }
    }

}
