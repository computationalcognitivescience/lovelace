module Jekyll::Tags
	class DProblemTag < Liquid::Block

		def initialize(tag_name, block_options, liquid_options)
		  super
		  @problemName = block_options.strip
		end

		def render(context)
			context.stack do
				@content = super.strip
			end
			
			inout = @content.split(';;')
			input = inout.first
			output = inout.last

			"<span class=\"problemtitle\">#{@problemName}</span><br /><span class=\"probleminout\">Input:</span> #{input}<br /><span class=\"probleminout\">Question:</span> #{output}"
		end
	end
	
	class FProblemTag < Liquid::Block

		def initialize(tag_name, block_options, liquid_options)
		  super
		  @problemName = block_options.strip
		end

		def render(context)
			context.stack do
				@content = super.strip
			end
			
			inout = @content.split(';;')
			input = inout.first
			output = inout.last

			"<span class=\"problemtitle\">#{@problemName}</span><br /><span class=\"probleminout\">Input:</span> #{input}<br /><span class=\"probleminout\">Output:</span> #{output}"
		end
	end
end


Liquid::Template.register_tag('dproblem', Jekyll::Tags::DProblemTag)
Liquid::Template.register_tag('fproblem', Jekyll::Tags::DProblemTag)
