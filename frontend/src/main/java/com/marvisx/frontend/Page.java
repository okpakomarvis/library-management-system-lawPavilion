package com.marvisx.frontend;

public class Page {
   private int size;
   private int number;
   private int totalElements;
   private int totalPages;

   public Page(int size, int number, int totalElements, int totalPages) {
      this.size = size;
      this.number = number;
      this.totalElements = totalElements;
      this.totalPages = totalPages;
   }
   public Page(){}

   public int getSize() {
      return size;
   }

   public void setSize(int size) {
      this.size = size;
   }

   public int getNumber() {
      return number;
   }

   public void setNumber(int number) {
      this.number = number;
   }

   public int getTotalElements() {
      return totalElements;
   }

   public void setTotalElements(int totalElements) {
      this.totalElements = totalElements;
   }

   public int getTotalPages() {
      return totalPages;
   }

   public void setTotalPages(int totalPages) {
      this.totalPages = totalPages;
   }
}
