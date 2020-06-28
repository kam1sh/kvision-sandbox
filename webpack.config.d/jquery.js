;(function() {
    const webpack = require('webpack')

    config.externals = {
        jquery: 'jQuery'
    }
    config.plugins.push(new webpack.ProvidePlugin({
	$: "jquery",
	jQuery: "jquery",
	"window.jQuery": "jquery"
    }));
})();
