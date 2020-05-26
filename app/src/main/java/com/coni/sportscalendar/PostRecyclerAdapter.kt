package com.coni.sportscalendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_post.view.*
import kotlin.math.min

class PostRecyclerAdapter(private val onPostClickListener: OnPostClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private var items = ArrayList<Post>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_post, parent, false), onPostClickListener)
    }

    override fun getItemCount(): Int
    {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        when (holder)
        {
            is PostViewHolder ->
            {
                holder.bind(items.get(position))
            }
        }
    }

    fun submitList (dataList :ArrayList<Post>)
    {
        items = dataList
    }

    class PostViewHolder constructor(itemView: View, val onPostListener :OnPostClickListener): RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        val textViewLocation = itemView.textView_location
        val textViewAuthor = itemView.textView_creator
        val textViewDescription = itemView.textView_description

        fun bind(post: Post)
        {
            textViewLocation.text = post.location
            textViewAuthor.text = post.authorName.toString()
            textViewDescription.text = post.description.substring(0, min(post.description.length, 150)) + " ..."

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onPostListener.onPostClick(adapterPosition)
        }
    }
    interface OnPostClickListener
    {
        fun onPostClick (position: Int);
    }
}