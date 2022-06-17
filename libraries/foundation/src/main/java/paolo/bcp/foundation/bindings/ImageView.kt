package paolo.bcp.foundation.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import paolo.bcp.foundation.R


@BindingAdapter("imageUrl", "imageErrorResource")
fun bindImageWithError(view: ImageView, imageUrl: String, imageErrorResource: String) {
    Picasso
        .get()
        .load(imageUrl.ifBlank { "https://nn.com/i" })
        .placeholder(R.drawable.ic_baseline_flag_24)
        .error(view.context.resources.getIdentifier(imageErrorResource, "drawable", view.context.packageName))
        .into(view)
}