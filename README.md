# JSON-Application

# Problem:
Given a URL with a JSON formatted text, use Android Studio to grab this information, parse the data retrieved from the server into a list of Java object, then display them into a RecyclerView.

# Solution:
1. Have a main activity with a button. Once this button is pressed, start a new activity, and begin to parse from the URL.

2. In the next activity, parse the data, and display it using a recyclerview.
  a) In onCreate, call the AsyncTask instance which will fetch the data from the given URL, put it into a string, then a JSON Array. Once done, set each JSON Object's data accordinglyfrom the JSON Array, creating multiple JSON Objects as you go.
  
3. After completion, set the recyclerview adapter, layoutmanager, etc. to display all parsed data, in a formatted, custom stlye, specified in the location_entry.xml layout.


# Reasoning Behind Technical Choices:

STARTER BUTTON: I chose to implement the first starter button on MainActivity so that the program would start when the user desired.

ASYNCTASK: AsyncTasks are perfect for short-term, background-thread, operations. They run without having to implement Threads or Runnables, or Handlers. It also allows the programmer work with the UI easily in onPreExecute() and onPostExecute() to update the user on what is going on. The ProgressDialog was perfect for this case, working as a loading screen. AsyncTask also utilizes many threads and is very well optimized.

RECYCLERVIEW: Because I had to implement RecyclerView, I created a LocationData class. This works with the ViewHolder to set data entries according to their JSON string key. Furthermore, RecyclerView also requires an Adapter, which is contained in the LocationAdapter class in my project. This is where all TextViews and the ImageView are assigned and instantiated.

LAYOUTS: MainActivity did not have much going on, so I used a RelativeLayout to place things nicely. It is slightly more costly, than LinearLayout, but allows moving things around in the X axis to be much easier. For the RecyclerView Layout for each entry, I used LinearLayout because of the time constraint. If time had allowed, I would have loved to create a CardView-RecyclerView for each JSON Object, which would be much more user-friendly. For the parsing and listing activity, I chose ConstraintLayout because the JSON package had an item named "Total," which I wanted to remain on the top. So, I made the RecyclerView constrained under the "Total" TextView, so that total would always stay in view. This is extremely expensive, and could probably be implemented with two LinearLayouts.


# Result:
Unfortunately, I was unable to get the data from the URL, seemingly because of the response code, 403. If the data could have been recieved, I believe the rest of my code would have worked very well, as everything else is implemented. If I had additional time, I would try to learn more about the HttpURLConnection class, since previous Http classes have been deprecated. Because of this, I was slowed down trying to get figure out most current method of recieving data from a URL.
