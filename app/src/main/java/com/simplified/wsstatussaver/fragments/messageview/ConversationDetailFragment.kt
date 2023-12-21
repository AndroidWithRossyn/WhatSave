/*
 * Copyright (C) 2023 Christians Martínez Alvarado
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by
 * the Free Software Foundation either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */
package com.simplified.wsstatussaver.fragments.messageview

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialFadeThrough
import com.simplified.wsstatussaver.R
import com.simplified.wsstatussaver.WhatSaveViewModel
import com.simplified.wsstatussaver.adapter.MessageAdapter
import com.simplified.wsstatussaver.database.Conversation
import com.simplified.wsstatussaver.database.MessageEntity
import com.simplified.wsstatussaver.databinding.FragmentMessagesBinding
import com.simplified.wsstatussaver.fragments.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ConversationDetailFragment : BaseFragment(R.layout.fragment_messages) {

    private val arguments by navArgs<ConversationDetailFragmentArgs>()
    private val viewModel: WhatSaveViewModel by activityViewModel()

    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!
    private var adapter: MessageAdapter? = null

    private val conversation: Conversation
        get() = arguments.extraConversation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMessagesBinding.bind(view)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        enterTransition = MaterialFadeThrough().addTarget(binding.container)
        reenterTransition = MaterialFadeThrough().addTarget(binding.container)

        setupToolbar()
        setupRecyclerView()

        viewModel.receivedMessages(conversation).observe(viewLifecycleOwner) {
            data(it)
        }
    }

    private fun setupToolbar() {
        binding.toolbar.title = getString(R.string.messages_from_x, conversation.name)
        statusesActivity.setSupportActionBar(binding.toolbar)
    }

    private fun setupRecyclerView() {
        adapter = MessageAdapter(requireContext(), arrayListOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun data(messages: List<MessageEntity>) {
        adapter?.data(messages)
        if (messages.isEmpty()) {
            findNavController().popBackStack()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateMenu(menu, menuInflater)
        menu.removeItem(R.id.action_settings)
        menu.removeItem(R.id.action_about)
    }
}