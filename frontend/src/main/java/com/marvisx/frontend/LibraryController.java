package com.marvisx.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import javafx.scene.control.Pagination;
import javafx.util.Duration;

public class LibraryController {
    @FXML
    private Label welcomeText;

    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, Integer> titleNumber;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, String> publishedDateColumn;
    @FXML private TableColumn<Book, String> isbnColumn;

    @FXML private TextField isbnField;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField publishedDateField;
    private int pageSize =6;

    @FXML private TextField searchField;
    @FXML
    private Pagination pagination;
    private static final String BASE_URL = "http://localhost:8080/api/books";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private int currentPage = 0;
    private  int pageTotal = 1;





    ObservableList<Book> data = FXCollections.observableArrayList();




    public LibraryController(){
    }
    @FXML
    public void initialize()  {


        //load book data from api backend
        fetchBooks(0, false);

        // Set the total number of pages
        pagination.setPageCount(pageTotal);
        // Set the current page index (0-based)
        pagination.setCurrentPageIndex(0);

        titleNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        publishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        bookTable.setOnMouseClicked(mouseEvent -> {
            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
            if (selectedBook == null) {
                showAlert("No Selection", "Please select a book to update.");
                return;
            }
            titleField.setText(selectedBook.getTitle());
            authorField.setText(selectedBook.getAuthor());
            isbnField.setText(selectedBook.getIsbn());
            publishedDateField.setText(selectedBook.getPublishedDate());
        });
        bookTable.setItems(data);

       //search
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            //call the delay event here
                pauseSearch().playFromStart();

        });



        // Add a listener to handle page changes for pagination
        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            fetchBooks(newIndex.intValue(), true);
            pagination.setCurrentPageIndex(newIndex.intValue());

        });

    }

    public void fetchBooks(int currentPage, boolean clear){
        try {

            String url = BASE_URL + "?page=" + currentPage + "&size=" + pageSize;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                BookResponse bookResponse = objectMapper.readValue(response.body(), BookResponse.class);
                List<Book> books = bookResponse.getContent() ;
                pageTotal =bookResponse.getPage().getTotalPages();
                currentPage= bookResponse.getPage().getNumber();
                if(clear){
                    data.clear();
                }
                data.addAll(books);
            } else {
                showAlert("Error", "Book not found.");
            }

        } catch (NumberFormatException e) {
            showAlert("Input Error", "Invalid book ID.");
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            showAlert("Error", "Failed to fetch book from server.");
        }

    }

    public void addBook(){
        String title = titleField.getText();
        String author = authorField.getText();
        String isbnFieldText = isbnField.getText();
        String publishText = publishedDateField.getText();

        if(!title.isEmpty() && !author.isEmpty() && !isbnFieldText.isEmpty() && !publishText.isEmpty()) {
            //
            String url = BASE_URL;
            Book newBook = new Book();
            newBook.setTitle(title);
            newBook.setAuthor(author);
            newBook.setIsbn(isbnFieldText);
            newBook.setPublishedDate(publishText);
            // Convert the object to JSON string

            try{
                String book = objectMapper.writeValueAsString(newBook);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(book))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 ||response.statusCode() == 201 ) {
                fetchBooks(0,true);
                showAlert("Success", "Book was successfully created");
            } else {

                showAlert("Error", "Book With ISBN "+newBook.getIsbn()+" Already Exist");
            }

        } catch (NumberFormatException e) {
            showAlert("Input Error", "Fail to create book");
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            showAlert("Error", e.getMessage());
        }

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields are required");
            alert.show();
        }
    }
    public void updateBook(){
        String title = titleField.getText();
        String author = authorField.getText();
        String isbnFieldText = isbnField.getText();
        String publishText = publishedDateField.getText();

        if(!title.isEmpty() && !author.isEmpty() && !isbnFieldText.isEmpty() && !publishText.isEmpty()) {
            //check if already exist in the database
            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();

            Book newBook = new Book();
            newBook.setTitle(title);
            newBook.setAuthor(author);
            newBook.setIsbn(isbnFieldText);
            newBook.setPublishedDate(publishText);
            String url = BASE_URL +"/"+newBook.getIsbn();
            // Convert the object to JSON string

            try{
                String book = objectMapper.writeValueAsString(newBook);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .PUT(HttpRequest.BodyPublishers.ofString(book))
                        .header("Content-Type", "application/json")
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 ||response.statusCode() == 201 ) {
                    fetchBooks(0,true);
                    showAlert("Success", "Book was successfully created");
                } else {
                    showAlert("Error", "Book not found.");
                }

            } catch (NumberFormatException e) {
                showAlert("Input Error", "Fail to delete book");
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
                showAlert("Error", e.getMessage());
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select a book to update.");
            alert.show();
        }
    }
    public void deleteBook(){
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();


        if(selectedBook.getId() != null) {
            try {

                String url = BASE_URL +"/"+selectedBook.getId();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .DELETE()
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 204 || response.statusCode() == 201 || response.statusCode() == 200) {
                 fetchBooks(0, true);
                    showAlert("Success", "Book was successfully deleted");
                } else {
                    showAlert("Error", "Book not found.");
                }

            } catch (NumberFormatException e) {
                showAlert("Input Error", "Invalid book ID.");
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
                showAlert("Error", e.getMessage());
            }


            return;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select a book to delete.");
            alert.show();
        }
    }
    public PauseTransition pauseSearch(){
        // PauseTransition with a delay of 500 milliseconds
        PauseTransition pause = new PauseTransition(Duration.millis(500));

        //add event listener for search
         pause.setOnFinished(event -> {
            String searchText = searchField.getText().toLowerCase();
            try {

                String url = BASE_URL + "/search?query=" + searchText + "&page=" + currentPage + "&size=" + pageSize;

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    BookResponse bookResponse = objectMapper.readValue(response.body(), BookResponse.class);
                    List<Book> books = bookResponse.getContent();
                    pageTotal = bookResponse.getPage().getTotalPages();
                    currentPage = bookResponse.getPage().getNumber();
                    data.clear();
                    data.addAll(books);
                } else {
                    showAlert("Error", "No Book Record Found.");
                }


            } catch (NumberFormatException e) {
                showAlert("Input Error", "Try with more search");
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
                showAlert("Error", e.getMessage());
            }

        });
         return pause;
    }
    public void refreshPage(){
        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
       publishedDateField.setText("");
       fetchBooks(0,true);
        // Set the total number of pages
        pagination.setPageCount(pageTotal);
        // Set the current page index (0-based)
        pagination.setCurrentPageIndex(0);
        //clear search field
        searchField.setText("");


    }
    /**
     * Displays an alert message.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}