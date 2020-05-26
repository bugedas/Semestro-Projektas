package com.coni.sportscalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_my_posts.*
import org.json.JSONArray

class MyPostsActivity : AppCompatActivity(), PostRecyclerAdapter.OnPostClickListener
{
    lateinit var userData :ProfileInfo
    private lateinit var postAdapter: PostRecyclerAdapter
    private var posts: ArrayList<Post>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_posts)
        val userJson = intent.getStringExtra(TeamSearchActivity.USER_DATA)
        userData = Gson().fromJson(userJson, ProfileInfo::class.java)

        initRecyclerView()
        fetchMyPosts()
    }

    private fun initRecyclerView ()
    {
        recyclerView_MyPosts.apply{
            layoutManager = LinearLayoutManager (this@MyPostsActivity)
            val spacing = TopSpacingItemDecoration(30)
            addItemDecoration(spacing)
            postAdapter = PostRecyclerAdapter(this@MyPostsActivity)
            adapter = postAdapter
        }
    }

    private val successFetchPostsResponse = Response.Listener <JSONArray>()
    { response ->
        val jsonParser = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        Log.d("LoginActivity", "NetworkResponse : ${response.toString()}")
        val dataArray: Array<Post> = jsonParser.fromJson(response.toString(), Array<Post>::class.java)//.toCollection(ArrayList())
        //val dataArrayList: ArrayList<Post> = ArrayList(listOf(*dataArray))
        posts = ArrayList(listOf(*dataArray))
        postAdapter.submitList(posts!!)
        postAdapter.notifyDataSetChanged()
    }

    private val failedFetchPostsResponse = Response.ErrorListener ()
    {
        /*Toast.makeText(
            this,
            "Could not find any posts!",
            Toast.LENGTH_LONG
        ).show()*/
        posts = ArrayList()
        postAdapter.submitList(posts!!)
        postAdapter.notifyDataSetChanged()
    }

    private fun fetchMyPosts()
    {
        Server.getInstance(this).getPosts(userData.id,successFetchPostsResponse,failedFetchPostsResponse)
    }

    override fun onPostClick(position: Int)
    {
        val postData: String = Gson().toJson(posts!![position])
        val userDataJson: String = Gson().toJson(userData)

        if(posts!![position].authorID != userData.id )
        {
            val intent = Intent(this,PostViewActivity::class.java)

            //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)

            intent.putExtra(TeamSearchActivity.POST_DATA, postData)
            intent.putExtra(TeamSearchActivity.USER_DATA, userDataJson)

            startActivity(intent)
        }
        else
        {
            val intent = Intent(this,PostOwnerViewActivity::class.java)

            //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)

            intent.putExtra(TeamSearchActivity.POST_DATA, postData)
            intent.putExtra(TeamSearchActivity.USER_DATA, userDataJson)

            startActivity(intent)
        }

    }

}