Android - MultiStateListView
============================

A simple Android `ListView` that lets you define **three states**:

* Loading
* Empty
* Error

Screenshots
-----------
![alt text](https://raw.github.com/jenzz/Android-MultiStateListView/master/assets/Screenshot1.png "Empty state")
![alt text](https://raw.github.com/jenzz/Android-MultiStateListView/master/assets/Screenshot2.png "Loading state")
![alt text](https://raw.github.com/jenzz/Android-MultiStateListView/master/assets/Screenshot3.png "Loaded state")
![alt text](https://raw.github.com/jenzz/Android-MultiStateListView/master/assets/Screenshot4.png "Error state")

Usage
-----
* Define the `MultiStateListView` via XML (use custom attributes to reference the state layouts):

```java
<com.jensdriller.libs.multistatelistview.MultiStateListView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        custom:emptyView="@layout/empty_view"
        custom:errorView="@layout/error_view"
        custom:loadingView="@layout/loading_view" />
```

* Or build it via Java:

```java
  MultiStateListView multiStateListView = new MultiStateListView.Builder(this)//
  .loadingView(R.layout.loading_view)//
  .emptyView(R.layout.empty_view)//
  .errorView(R.layout.error_view)//
  .build();
```

* Show the desired state based on your logic:

```java
  multiStateListView.showLoadingView();
```
```java
  multiStateListView.showEmptyView();
```
```java
  multiStateListView.showErrorView();
```

Example
-------
Check out the [demo](https://github.com/jenzz/Android-MultiStateListView/tree/master/src/com/jensdriller/libs/multistatelistview/demo) for an example implementation.