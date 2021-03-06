package com.coni.sportscalendar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_team_search.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList


class TeamSearchActivity : AppCompatActivity(), PostRecyclerAdapter.OnPostClickListener
{

    companion object
    {
        const val POST_DATA: String = "POST_DATA"
        const val USER_DATA: String = "USER_DATA"
    }

    //private var authorised: Boolean = false
    private var userInfo: ProfileInfo? = null
    private lateinit var postAdapter: PostRecyclerAdapter
    private var posts: ArrayList<Post>? = null
    @Override
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_search)

        supportActionBar?.title = "Events"
        Server.getInstance(this).getUserInfo(successGetUserInfoResponse)


        //while(!authorised)


        initRecyclerView()
    }

    @Override
    override fun onResume() {
        super.onResume()
        fetchPosts()
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

    private fun fetchPosts()
    {
        Server.getInstance(this).getPosts(successFetchPostsResponse,failedFetchPostsResponse)
    }
    private fun initRecyclerView ()
    {
        recyclerView_Posts.apply{
            layoutManager = LinearLayoutManager (this@TeamSearchActivity)
            val spacing = TopSpacingItemDecoration(30)
            addItemDecoration(spacing)
            postAdapter = PostRecyclerAdapter(this@TeamSearchActivity)
            adapter = postAdapter
        }
    }



    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            R.id.menu_sign_out ->
            {
                Server.getInstance(this).requestLogOut(successLogOutResponse)

            }
            R.id.create_post ->
            {
                val intent = Intent(this,CreatePostActivity::class.java)
                startActivity(intent)
            }
            R.id.profile ->
            {
                val intent = Intent(this,MyProfile::class.java)
                startActivity(intent)
            }
            R.id.my_posts ->
            {
                val userData: String = Gson().toJson(userInfo)
                val intent = Intent(this,MyPostsActivity::class.java)
                intent.putExtra(TeamSearchActivity.USER_DATA, userData)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val successLogOutResponse = Response.Listener <JSONObject>()
    {
        Server.getInstance(this).resetCookies()
        val intent = Intent(this,LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onPostClick(position: Int)
    {
        val postData: String = Gson().toJson(posts!![position])
        val userData: String = Gson().toJson(userInfo)

        if(posts!![position].authorID != userInfo!!.id )
        {
            val intent = Intent(this,PostViewActivity::class.java)

            //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)

            intent.putExtra(TeamSearchActivity.POST_DATA, postData)
            intent.putExtra(TeamSearchActivity.USER_DATA, userData)

            startActivity(intent)
        }
        else
        {
            val intent = Intent(this,PostOwnerViewActivity::class.java)

            //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)

            intent.putExtra(TeamSearchActivity.POST_DATA, postData)
            intent.putExtra(TeamSearchActivity.USER_DATA, userData)

            startActivity(intent)
        }

    }

    private val successGetUserInfoResponse = Response.Listener <JSONObject>()
    { response ->
        val jsonParser = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        userInfo = jsonParser.fromJson(response.toString(), ProfileInfo::class.java)
    }
}