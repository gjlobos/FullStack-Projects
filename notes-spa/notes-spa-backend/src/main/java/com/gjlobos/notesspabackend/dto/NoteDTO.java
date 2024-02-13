package com.gjlobos.notesspabackend.dto;

import java.util.Date;
import java.util.Set;

public class NoteDTO {
    private Long id;
    private String title;
    private String content;
    private boolean isArchived;
    private Date dateLastEdit;
    private Set<Long> categoryIds;

    public NoteDTO() {
    }

    public NoteDTO(Long id, String title, String content, boolean isArchived, Date dateLastEdit, Set<Long> categoryIds) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isArchived = isArchived;
        this.dateLastEdit = dateLastEdit;
        this.categoryIds = categoryIds;
    }



    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        this.isArchived = archived;
    }

    public Date getDateLastEdit() {
        return dateLastEdit;
    }

    public void setDateLastEdit(Date dateLastEdit) {
        this.dateLastEdit = dateLastEdit;
    }

    public Set<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Set<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

}
