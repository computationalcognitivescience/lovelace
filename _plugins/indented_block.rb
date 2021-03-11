module Jekyll::Tags
	class IndentTag < Liquid::Block

		def initialize(tag_name, text, tokens)
		  super
		  @text = text
		end

		def render(context)
			content = super
			
			"<div class='indent-#{@text}' markdown='1'>#{content}</div>"
		end
	end
end

Liquid::Template.register_tag('indent', Jekyll::Tags::IndentTag)
