## Liquid tag 'question' used to add a question
## in the main text area of the layout
## Usage
# {% question %}
# What is this?
# {% endquestion %}

module Jekyll::Tags
	class QuestionTag < Liquid::Block

		def initialize(tag_name, block_options, liquid_options)
		  super
		  # @questionNR = block_options.strip
		  # @questionID = "question-#{block_options.strip}"
		end

		def render(context)
      site = context.registers[:site]
      converter = site.find_converter_instance(::Jekyll::Converters::Markdown)


			if(context["questionNR"])
				context["questionNR"] = context["questionNR"] + 1
			else
				context["questionNR"] = 1
			end

			@questionNR = context["questionNR"]

			if(context["parents"])
				@questionID = "question-#{context["parents"].join('.')}.#{context["questionNR"]}"
				if(context["parents"].length == 0)
					@questionLabel = "#{context["questionNR"]}"
				else
					@questionLabel = "#{context["parents"].join('.')}.#{context["questionNR"]}"
				end
			else
				@questionID = "question-#{context["questionNR"]}"
				@questionLabel = "#{context["questionNR"]}"
				context["parents"] = Array.new
			end

      context.stack do
				# go one level deeper
				context["questionID"] = @questionID
				context["parents"] = context["parents"].push(@questionNR)
				context["questionNR"] = 0
				context["answer_idx"] = 1
				@content = converter.convert(super) #.strip
				# reset to current level
				context["parents"].pop
			end

      chapterNR = context.registers[:page]['chapter']
      "<div class=\"question\" id=\"question-#{@questionLabel}\"><p><em>Question #{chapterNR}.#{@questionLabel}:</em>#{@content}</p></div>"
		end
	end


	class HiddenAnswerTag < Liquid::Block
		def initialize(tag_name, block_options, liquid_options)
		  super
		  @title = block_options.strip
		end

		def render(context)
			site = context.registers[:site]
      converter = site.find_converter_instance(::Jekyll::Converters::Markdown)
			content = converter.convert(super)

      idx = context["answer_idx"]


      # increment for the next collapsible
      if(idx)
        # question mode
        questionID = context["questionID"]
				context["questionNR"] = context["questionNR"] - 1
  			answerID = "#{questionID}-answer-#{idx}"
  			headingID = "#{questionID}-heading-#{idx}"
        context["answer_idx"] = idx + 1
      else
        # solo mode
        if(context["hint_idx"])
          context["hint_idx"] = context["hint_idx"] + 1
        else
          context["hint_idx"] = 1
        end
        answerID = "hint-#{context["hint_idx"]}"
        headingID = "#{answerID}-heading-#{answerID}"
      end
			content = super.strip

      "<div class=\"answer\" id=\"#{headingID}\"><p><a onclick=\"document.getElementById('#{answerID}').style.display = document.getElementById('#{answerID}').style.display === 'none' ? '' : 'none';\">#{@title}</a></p><div id=\"#{answerID}\" style=\"display: none;\"><p>#{content}</p></div></div>"

		end
	end

  class AnswerTag < Liquid::Block
		def initialize(tag_name, block_options, liquid_options)
		  super
		  @title = block_options.strip
		end

		def render(context)
			site = context.registers[:site]
      converter = site.find_converter_instance(::Jekyll::Converters::Markdown)
			content = converter.convert(super)

			questionID = context["questionID"]
			idx = context["answer_idx"]
			answerID = "#{questionID}-answer-#{idx}"
			headingID = "#{questionID}-heading-#{idx}"

			# increment for the next collapsible
			context["answer_idx"] = idx + 1
      content = super.strip
			"<div class='answer' id='#{headingID}'><p><em>#{@title}</em>: #{content}</p></div>"

		end
	end

  class StopThinkTag < Liquid::Block
    def initialize(tag_name, block_options, liquid_options)
      super
    end

    def render(context)
      content = super
      "<p class=\"stopandthink\"><em>Stop and think: </em>#{content}</p>"
    end
  end
end

Liquid::Template.register_tag('question', Jekyll::Tags::QuestionTag)
Liquid::Template.register_tag('hidden', Jekyll::Tags::HiddenAnswerTag)
Liquid::Template.register_tag('answer', Jekyll::Tags::AnswerTag)
Liquid::Template.register_tag('stopandthink', Jekyll::Tags::StopThinkTag)
