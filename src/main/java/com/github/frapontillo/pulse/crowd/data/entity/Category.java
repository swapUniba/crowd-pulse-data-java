package com.github.frapontillo.pulse.crowd.data.entity;

/**
 * Category for {@link Tag} elements
 *
 * @author Francesco Pontillo
 */
public class Category {
    private String text;
    private boolean stopWord;

    public Category() {
    }

    public Category(String text) {
        this.text = text;
        this.stopWord = false;
    }

    /**
     * Get the text of the Category.
     *
     * @return The text of the Category.
     */
    public String getText() {
        return text;
    }

    /**
     * Set the text of the Category.
     *
     * @param text The text of the Category.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Check if the Tag is considered a stop word.
     *
     * @return {@code true} if the Tag is a stop word, {@code false} otherwise.
     */
    public boolean isStopWord() {
        return stopWord;
    }

    /**
     * Mark the Tag as a stop word or not.
     *
     * @param stopWord {@code true} if the Tag is a stop word, {@code false} otherwise.
     */
    public void setStopWord(boolean stopWord) {
        this.stopWord = stopWord;
    }
}
